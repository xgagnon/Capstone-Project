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

    public static final String userIdField = "userId";
    public static final String emailField = "email";
    public static final String firstNameField = "firstName";
    public static final String lastNameField = "lastName";
    public static final String phoneField = "phone";
    public static final String addressField = "address";
    public static final String passwordField = "password";
    public static final String roleField = "role";
    public static final String cartField = "cart";
    public static final String likesField = "likes";
    public static final String transactionsField = "transactions";

    private static UserDB instance;

    private UserDB() {
    }

    public static synchronized UserDB getInstance() {
        if(instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void insert(User user) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append(userIdField, user.getUserId())
                        .append(emailField, user.getEmail())
                        .append(firstNameField, user.getFirstName())
                        .append(lastNameField, user.getLastName())
                        .append(phoneField, user.getPhone())
                        .append(addressField, user.getAddress())
                        .append(passwordField, user.getPassword())
                        .append(roleField, user.getRole())
                        .append(cartField, user.getCart())
                        .append(likesField, user.getLikes())
                        .append(transactionsField, user.getTransactions())
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
                        .append(userIdField, user.getUserId())
                        .append(emailField, user.getEmail())
                        .append(firstNameField, user.getFirstName())
                        .append(lastNameField, user.getLastName())
                        .append(phoneField, user.getPhone())
                        .append(addressField, user.getAddress())
                        .append(passwordField, user.getPassword())
                        .append(roleField, user.getRole())
                        .append(cartField, user.getCart())
                        .append(likesField, user.getLikes())
                        .append(transactionsField, user.getTransactions())
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
    public User find(String email){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        User user;

        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(COLLECTION_NAME, User.class);
            user = collection.find(Filters.eq(emailField, email)).projection(Projections.excludeId()).first();
        }

        return user;
    }

    public void update(User user) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document().append(userIdField,  user.getUserId());

            Bson updates = Updates.combine(
                    Updates.set(emailField, user.getEmail()),
                    Updates.set(firstNameField, user.getFirstName()),
                    Updates.set(lastNameField, user.getLastName()),
                    Updates.set(phoneField, user.getPhone()),
                    Updates.set(addressField, user.getAddress()),
                    Updates.set(passwordField, user.getPassword()),
                    Updates.set(roleField, user.getRole()),
                    Updates.set(cartField, user.getCart()),
                    Updates.set(likesField, user.getLikes()),
                    Updates.set(transactionsField, user.getTransactions())
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

            Bson query = Filters.eq(userIdField, user.getUserId());

            try {
                collection.deleteOne(query);
            } catch (MongoException me) {
                System.err.println("Unable to delete due to an error: " + me);
            }
        }
    }
}