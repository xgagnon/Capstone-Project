package dbaccess;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import models.Counter;
import models.Transaction;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import services.MongoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class TransactionDB {

    public static final String URI = "mongodb://localhost:27017";
    public static final String DB_NAME = "image_store_db";
    public static final String COLLECTION_NAME = "transactions";

    public static final String transactionIdField = "transactionId";
    public static final String dateField = "date";
    public static final String priceField = "price";
    public static final String buyerEmailField = "buyerEmail";
    public static final String statusField = "status";
    public static final String purchasedImagesField = "purchasedImages";

    private static TransactionDB instance;

    private TransactionDB() {
    }

    public static synchronized TransactionDB getInstance() {
        if (instance == null) {
            instance = new TransactionDB();
        }
        return instance;
    }

    public void insert(Transaction transaction) {

        CounterDB counterDB = CounterDB.getInstance();
        Counter counter = counterDB.find("transactionId");
        counter.incrementSeq();
        counterDB.update(counter);

        try (MongoClient mongoClient = new MongoService().getClient()) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append(transactionIdField, counter.getSeq())
                        .append(dateField, new Date())
                        .append(priceField, transaction.getPrice())
                        .append(buyerEmailField, transaction.getBuyerEmail())
                        .append(statusField, transaction.getStatus())
                        .append(purchasedImagesField, transaction.getPurcahsedImages())
                );
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public static Transaction find(long transactionID){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Transaction transaction;

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Transaction> collection = database.getCollection(COLLECTION_NAME, Transaction.class);
            transaction = collection.find(Filters.eq(transactionIdField, transactionID)).projection(Projections.excludeId()).first();
        }

        return transaction;
    }

    public static List<Transaction> findAll(String email){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        List<Transaction> transactions = new ArrayList<>();

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Transaction> collection = database.getCollection(COLLECTION_NAME, Transaction.class);
            FindIterable<Transaction> iterAll = collection.find(Filters.eq(buyerEmailField, email)).projection(Projections.excludeId());
            iterAll.iterator().forEachRemaining(transactions::add);
        }

        return transactions;
    }

}
