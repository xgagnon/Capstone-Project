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

    ImageDB imageDb = ImageDB.getInstance();
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
    public void testFindAll(){
        //Change expected size to match current Atlas collection count for images
        //Run this test alone
        int expectedSize = 31;

        List<Image> images = imageDb.findAll();

        assertTrue(images.size() > 0);
        assertTrue(images.size() > 1);
        assertEquals(expectedSize,images.size());
    }

}
