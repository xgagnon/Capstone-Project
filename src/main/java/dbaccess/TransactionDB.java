package dbaccess;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import models.Transaction;
import models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append(transactionIdField, transaction.getTransactionId())
                        .append(dateField, transaction.getDate())
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


    public void insertMany(List<Transaction> transactions) {

        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            List<Document> transactionsList = new ArrayList<>();

            for(Transaction transaction : transactions) {
                transactionsList.add(new Document()
                        .append("_id", new ObjectId())
                        .append(transactionIdField, transaction.getTransactionId())
                        .append(dateField, transaction.getDate())
                        .append(priceField, transaction.getPrice())
                        .append(buyerEmailField, transaction.getBuyerEmail())
                        .append(statusField, transaction.getStatus())
                        .append(purchasedImagesField, transaction.getPurcahsedImages())
                );
            }

            try {
                collection.insertMany(transactionsList);
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public Transaction find(String email){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Transaction transaction;

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Transaction> collection = database.getCollection(COLLECTION_NAME, Transaction.class);
            transaction = collection.find(Filters.eq(buyerEmailField, email)).projection(Projections.excludeId()).first();
        }

        return transaction;
    }

    public void update(Transaction transaction) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document().append(transactionIdField,  transaction.getTransactionId());

            Bson updates = Updates.combine(
                    Updates.set(transactionIdField, transaction.getTransactionId()),
                    Updates.set(dateField, transaction.getDate()),
                    Updates.set(priceField, transaction.getPrice()),
                    Updates.set(buyerEmailField, transaction.getBuyerEmail()),
                    Updates.set(statusField, transaction.getStatus()),
                    Updates.set(purchasedImagesField, transaction.getPurcahsedImages())
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                collection.updateOne(query, updates, options);
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }

    public void delete(Transaction transaction) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson query = Filters.eq(transactionIdField, transaction.getTransactionId());

            try {
                collection.deleteOne(query);
            } catch (MongoException me) {
                System.err.println("Unable to delete due to an error: " + me);
            }
        }
    }


}
