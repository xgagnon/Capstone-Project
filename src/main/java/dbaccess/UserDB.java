package dbaccess;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.FirebaseDatabase;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import enums.Role;
import models.Counter;
import models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import services.FireBaseService;
import services.MongoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class UserDB {

    //public static final String URI = "mongodb://localhost:27017";
    public static final String DB_NAME = "image_store_db";
    public static final String COLLECTION_NAME = "users";

    public static final String userIdField = "userId";
    public static final String uidField = "uid";
    public static final String emailField = "email";
    public static final String firstNameField = "firstName";
    public static final String lastNameField = "lastName";
    public static final String phoneField = "phone";
    public static final String addressField = "address";
    public static final String passwordField = "password";
    public static final String statusField = "status";
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

    public void insert(User user) throws FirebaseAuthException, IOException {

        CounterDB counterDB = CounterDB.getInstance();
        Counter counter = counterDB.find("userid");
        counter.incrementSeq();
        counterDB.update(counter);

        try (MongoClient mongoClient = new MongoService().getClient()) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            try {
                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(user.getEmail())
                        .setEmailVerified(false)
                        .setPassword(user.getPassword())
                        .setDisabled(false);

                UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append(userIdField, counter.getSeq())
                        .append(uidField, userRecord.getUid())
                        .append(emailField, user.getEmail())
                        .append(firstNameField, user.getFirstName())
                        .append(lastNameField, user.getLastName())
                        .append(phoneField, user.getPhone())
                        .append(addressField, user.getAddress())
                        .append(roleField, user.getRole())
                        .append(statusField, user.getStatus())
                        .append(cartField, user.getCart())
                        .append(likesField, user.getLikes())
                        .append(transactionsField, user.getTransactions())
                );

            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }
    
    public User find(String uid){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        User user;

        //try (MongoClient mongoClient = MongoClients.create(URI)) {
        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection(COLLECTION_NAME, User.class);
            user = collection.find(Filters.eq(uidField, uid)).projection(Projections.excludeId()).first();
        }

        return user;
    }

    public void update(User user) {
        //try (MongoClient mongoClient = MongoClients.create(URI)) {
        User oldUser = find(user.getUid());

        String newEmail = user.getEmail();
        if (newEmail == null) {
            newEmail = oldUser.getEmail();
        }
        String newFirstName = user.getFirstName();
        if(newFirstName == null) {
            newFirstName = oldUser.getFirstName();
        }
        String newLastName = user.getLastName();
        if (newLastName == null) {
            newLastName = oldUser.getLastName();
        }
        long newPhone = user.getPhone();
        if (newPhone == 0) {
            newPhone = oldUser.getPhone();
        }
        String newAddress = user.getAddress();
        if (newAddress == null) {
            newAddress = oldUser.getAddress();
        }
        Role newRole = user.getRole();
        if (newRole == null) {
            newRole = oldUser.getRole();
        }
        String newStatus = user.getStatus();
        if (newStatus == null) {
            newStatus = oldUser.getStatus();
        }

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document().append(uidField,  user.getUid());

            Bson updates = Updates.combine(
                    Updates.set(emailField, newEmail),
                    Updates.set(firstNameField, newFirstName),
                    Updates.set(lastNameField, newLastName),
                    Updates.set(phoneField, newPhone),
                    Updates.set(addressField, newAddress),
                    Updates.set(roleField, newRole),
                    Updates.set(statusField, newStatus),
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
        //try (MongoClient mongoClient = MongoClients.create(URI)) {
        try (MongoClient mongoClient = new MongoService().getClient()) {
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