package dbaccess;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import models.Counter;
import models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class CounterDB {

    public static final String URI = "mongodb://localhost:27017";
    public static final String DB_NAME = "image_store_db";
    public static final String COLLECTION_NAME = "counters";


    private static CounterDB instance;

    private CounterDB() {
    }

    public static synchronized CounterDB getInstance() {
        if(instance == null) {
            instance = new CounterDB();
        }
        return instance;
    }

    public Counter find(String id){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Counter counter;

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Counter> collection = database.getCollection(COLLECTION_NAME, Counter.class);
            counter = collection.find(Filters.eq("_id", id)).first();
        }

        return counter;
    }

    public void update(Counter counter) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document().append("_id",  counter.getId());

            Bson updates = Updates.combine(
                    Updates.set("seq", counter.getSeq())
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                collection.updateOne(query, updates, options);
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }
}