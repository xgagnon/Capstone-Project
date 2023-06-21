package dbaccess;

import enums.Tags;
import models.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static enums.Tags.tag;
import static org.junit.Assert.assertEquals;

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
    public void testInsert(){
        long imageId = 123456789012L;
        String title = "Image 1";
        String description = "Image 1 Description";
        int seller = 12345678;
        int likes = 100;
        int views = 500;
        double price = 9.99;
        String status = "sold";
        String imageLocation = "/path/to/image1.jpg";
        Tags tag1  = tag;

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
        image.setTags(tag);

        imageDb.insert(image);

        Image foundImage = imageDb.find(image.getImageId());

        assertEquals(image.getImageId(),foundImage.getImageId());




    }
}
