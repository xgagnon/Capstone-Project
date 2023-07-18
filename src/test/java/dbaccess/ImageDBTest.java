package dbaccess;

import enums.Tags;
import models.Image;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ImageDBTest {

    ImageDB imageDb;

    @Before
    public void setUp() {
        imageDb = ImageDB.getInstance();
    }

    @After
    public void tearDown(){

    }
    @Test
    public void testFind(){
        long imageId = 1000000008L;
        String title = "Image 8";
        String description = "Image 8 Description";
        int seller = 12345678;
        int likes = 110;
        int views = 700;
        double price = 21.99;
        String status = "sold";
        String imageLocation = "/path/to/image8.jpg";
        ArrayList<Tags> tags  = new ArrayList<>();
        tags.add(Tags.tag01);
        tags.add(Tags.tag05);

        Image image = imageDb.find(imageId);

        assertEquals(imageId,image.getImageId());
        assertEquals(title,image.getTitle());
        assertEquals(description,image.getDescription());
        assertEquals(seller,image.getSeller());
        assertEquals(likes,image.getLikes());
        assertEquals(views,image.getViews());
        assertEquals(price,image.getPrice(),.001);
        assertEquals(status,image.getStatus());
        assertEquals(imageLocation,image.getImageLocation());
        assertEquals(tags,image.getTags());
    }
    @Test
    public void testInsert(){
        int imageId = 876543210;
        String title = "Image 2";
        String description = "Image 2 Description";
        int seller = 87654321;
        int likes = 50;
        int views = 200;
        double price = 19.99;
        String status = "available";
        String imageLocation = "/path/to/image2.jpg";
        ArrayList<Tags> tags  = new ArrayList<>();
        tags.add(Tags.tag03);
        tags.add(Tags.tag04);



        Image image = new Image();
        image.setImageId(imageId);
        image.setTitle(title);
        image.setDescription(description);
        image.setSeller(seller);
        image.setLikes(likes);
        image.setViews(views);
        image.setPrice(price);
        image.setStatus(status);
        image.setImageLocation(imageLocation);
        image.setTags(tags);

        imageDb.insert(image);

        Image foundImage = imageDb.find(image.getImageId());

        assertEquals(image.getImageId(),foundImage.getImageId());

    }
    @Test
    public void testUpdate(){
        int imageId = 12863254;
        String title = "Image 3";
        String description = "Image 3 Description";
        int seller = 12345678;
        int likes = 75;
        int views = 300;
        double price = 14.99;
        String status = "sold";
        String imageLocation = "/path/to/image3.jpg";
        ArrayList<Tags> tags  = new ArrayList<>();
        tags.add(Tags.tag03);
        tags.add(Tags.tag04);

        Image image = new Image();
        image.setImageId(imageId);
        image.setTitle(title);
        image.setDescription(description);
        image.setSeller(seller);
        image.setLikes(likes);
        image.setViews(views);
        image.setPrice(price);
        image.setStatus(status);
        image.setImageLocation(imageLocation);
        image.setTags(tags);

        imageDb.insert(image);
        Image foundImage = imageDb.find(image.getImageId());
        assertEquals(image.getImageId(),foundImage.getImageId());
    }
    @Test
    public void testDelete(){
        int imageId = 12345678;
        String title = "Image 4";
        String description = "Image 4 Description";
        int seller = 87654321;
        int likes = 120;
        int views = 800;
        double price = 24.99;
        String status = "on hold";
        String imageLocation = "/path/to/image4.jpg";
        ArrayList<Tags> tags  = new ArrayList<>();
        tags.add(Tags.tag01);
        tags.add(Tags.tag12);

        Image image = new Image();
        image.setImageId(imageId);
        image.setTitle(title);
        image.setDescription(description);
        image.setSeller(seller);
        image.setLikes(likes);
        image.setViews(views);
        image.setPrice(price);
        image.setStatus(status);
        image.setImageLocation(imageLocation);
        image.setTags(tags);

        imageDb.insert(image);
        Image foundImage = imageDb.find(image.getImageId());
        assertNotNull(foundImage);

        imageDb.delete(foundImage);
        foundImage = imageDb.find(image.getImageId());
        assertNull(foundImage);

    }
    @Test
    public void testInsertMany(){
        Image image1 = new Image();
        Image image2 = new Image();
        List<Image> images = new ArrayList<>();

        int imageId = 1000000008;
        String title = "Image 3";
        String description = "Image 3 Description";
        int seller = 12345678;
        int likes = 75;
        int views = 300;
        double price = 14.99;
        String status = "sold";
        String imageLocation = "/path/to/image3.jpg";
        ArrayList<Tags> tags  = new ArrayList<>();
        tags.add(Tags.tag01);
        tags.add(Tags.tag02);

        image1.setImageId(imageId);
        image1.setTitle(title);
        image1.setDescription(description);
        image1.setSeller(seller);
        image1.setLikes(likes);
        image1.setViews(views);
        image1.setPrice(price);
        image1.setStatus(status);
        image1.setImageLocation(imageLocation);
        image1.setTags(tags);
        images.add(image1);

        int imageId2 = 100000009;
        String title2 = "Image 1";
        String description2 = "Image 1 Description";
        int seller2 = 12345678;
        int likes2 = 100;
        int views2 = 500;
        double price2 = 9.99;
        String status2 = "sold";
        String imageLocation2 = "/path/to/image1.jpg";
        ArrayList<Tags> tags2  = new ArrayList<>();
        tags2.add(Tags.tag03);
        tags2.add(Tags.tag04);

        image2.setImageId(imageId2);
        image2.setTitle(title2);
        image2.setDescription(description2);
        image2.setSeller(seller2);
        image2.setLikes(likes2);
        image2.setViews(views2);
        image2.setPrice(price2);
        image2.setStatus(status2);
        image2.setImageLocation(imageLocation2);
        image2.setTags(tags2);
        images.add(image2);

        imageDb.insertMany(images);
        Image foundImage0 = imageDb.find(images.get(0).getImageId());
        Image foundImage1 = imageDb.find(images.get(1).getImageId());
        assertNotNull(foundImage0);
        assertNotNull(foundImage1);


    }

}
