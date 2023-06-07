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

public class UserDB {

    public static final String URI = "mongodb://localhost:27017";
    public static final String DB_NAME = "image_store_db";
    public static final String COLLECTION_NAME = "users";

    public void insert(User user) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("user_id", user.getUser_id())
                        .append("email", user.getEmail())
                        .append("first_name", user.getFirst_name())
                        .append("last_name", user.getLast_name())
                        .append("phone", user.getPhone())
                        .append("address", user.getAddress())
                        .append("password", user.getPassword())
                        .append("email", user.getEmail())
                        .append("cart", user.getCart())
                        .append("likes", user.getLikes())
                        .append("transaction_history", user.getTransactionHistory())
                );
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public void insertMany(List<User> users) {

        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            List<Document> userList = new ArrayList<>();

            for(User user : users) {
                userList.add(new Document()
                        .append("_id", new ObjectId())
                        .append("user_id", user.getUser_id())
                        .append("email", user.getEmail())
                        .append("first_name", user.getFirst_name())
                        .append("last_name", user.getLast_name())
                        .append("phone", user.getPhone())
                        .append("address", user.getAddress())
                        .append("password", user.getPassword())
                        .append("email", user.getEmail())
                        .append("cart", user.getCart())
                        .append("likes", user.getLikes())
                        .append("transaction_history", user.getTransactionHistory())
                );
            }

            try {
                collection.insertMany(userList);
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    //Find
    public User find(int userId){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        User user;

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(COLLECTION_NAME, User.class);
            user = collection.find(Filters.eq("user_id", userId)).projection(Projections.excludeId()).first();
        }

        return user;
    }

    public void update(User user) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document().append("user_id",  user.getUser_id());

            Bson updates = Updates.combine(
                    Updates.set("email", user.getEmail()),
                    Updates.addToSet("first_name", user.getFirst_name()),
                    Updates.addToSet("last_name", user.getLast_name()),
                    Updates.addToSet("phone", user.getPhone()),
                    Updates.addToSet("address", user.getAddress()),
                    Updates.addToSet("password", user.getPassword()),
                    Updates.addToSet("role", user.getRole()),
                    Updates.addToSet("cart", user.getCart()),
                    Updates.addToSet("likes", user.getLikes()),
                    Updates.addToSet("transaction_history", user.getTransactionHistory())
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                collection.updateOne(query, updates, options);
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }

    public void delete(User user) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson query = Filters.eq("user_id", user.getUser_id());

            try {
                collection.deleteOne(query);
            } catch (MongoException me) {
                System.err.println("Unable to delete due to an error: " + me);
            }
        }
    }
}