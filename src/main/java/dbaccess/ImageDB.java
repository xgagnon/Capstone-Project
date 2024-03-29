package dbaccess;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import models.Counter;
import models.Image;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import services.MongoService;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ImageDB {

    public static final String DB_NAME = "image_store_db";
    public static final String COLLECTION_NAME = "images";

    public static final String imageIdField = "imageId";
    public static final String titleField = "title";

    public static final String descriptionField  = "description";
    public static final String sellerField = "seller";
    public static final String likesField =  "likes";
    public static final String viewsField =  "views";
    public  static final String priceField = "price";
    public static final String statusField = "status";
    public static final String imageLocationField = "imageLocation";

//    public static final String descriptionField = "description";
//    public static final String sellerField = "seller";
//    public static final String likesField = "likes";
//    public static final String viewsField = "views";
//    public static final String priceField = "price";
//    public static final String statusField = "status";
//    public static final String imageLocationField = "imageLocation";


    public static final String tagsField = "tags";

    private static ImageDB instance;

    private ImageDB() {
    }

    public static synchronized ImageDB getInstance() {
        if (instance == null) {
            instance = new ImageDB();
        }
        return instance;
    }

    public void insert(Image image) {

        CounterDB counterDB = CounterDB.getInstance();
        Counter counter = counterDB.find("imageId");
        counter.incrementSeq();
        counterDB.update(counter);

        try (MongoClient mongoClient = new MongoService().getClient()) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append(imageIdField, counter.getSeq())
                        .append(titleField, image.getTitle())
                        .append(descriptionField, image.getDescription())
                        .append(sellerField, image.getSeller())
                        .append(likesField, image.getLikes())
                        .append(viewsField, image.getViews())
                        .append(priceField, image.getPrice())
                        .append(statusField, image.getStatus())
                        .append(imageLocationField, image.getImageLocation())
                        .append(tagsField,image.getTags())
                        .append(tagsField, image.getTags())
                );
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public Image find(long imageId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Image image;

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Image> collection = database.getCollection(COLLECTION_NAME, Image.class);
            image = collection.find(Filters.eq(imageIdField, imageId)).projection(Projections.excludeId()).first();
        }

        return image;
    }

    public void update(Image image) {

        try (MongoClient mongoClient = new MongoService().getClient()) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            Document query = new Document().append(imageIdField,  image.getImageId());
            Bson updates = Updates.combine(
                    Updates.set(imageIdField, image.getImageId()),
                    Updates.set(titleField, image.getTitle()),
                    Updates.set(descriptionField, image.getDescription()),
                    Updates.set(sellerField, image.getSeller()),
                    Updates.set(likesField, image.getLikes()),
                    Updates.set(viewsField, image.getViews()),
                    Updates.set(priceField, image.getPrice()),
                    Updates.set(statusField, image.getStatus()),
                    Updates.set(imageLocationField, image.getImageLocation()),
                    Updates.set(tagsField,image.getTags())
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                collection.updateOne(query, updates, options);
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }

    public void delete(Image image) {
        try (MongoClient mongoClient = new MongoService().getClient()) {

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson query = Filters.eq(imageIdField, image.getImageId());

            try {
                collection.deleteOne(query);
            } catch (MongoException me) {
                System.err.println("Unable to delete due to an error: " + me);
            }
        }
    }

    public Image findByTag(String tag){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        Image image;

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Image> collection = database.getCollection(COLLECTION_NAME, Image.class);
            image = collection.find(Filters.eq(tagsField, tag)).projection(Projections.excludeId()).first();
        }
        return image;
    }

    public List<Image> findAll() {

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        List<Image> images = new ArrayList<>();

        try (MongoClient mongoClient = new MongoService().getClient()) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Image> collection = database.getCollection(COLLECTION_NAME, Image.class);
            FindIterable<Image> iterAll = collection.find().projection(Projections.excludeId());
            iterAll.iterator().forEachRemaining(images::add);
        }

        return images;
    }
}
