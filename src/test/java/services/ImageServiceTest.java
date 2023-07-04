package services;

import dbaccess.ImageDB;
import exceptions.ImageException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageServiceTest {

    ImageDB imageDb = ImageDB.getInstance();


    @Test
    public void testImageError() {
        String expectedCode = "E1003";
        try {
            throw new ImageException("");
        } catch (ImageException e) {
            assertEquals(expectedCode, e.getCode());
        }
    }

    @Test
    public void testInsertImage() {
    }


    @Test
    public void testInsertExistingImage() {
    }

    @Test
    public void testInsertNullImage() {

    }

    @Test
    public void testInsertManyImage() {
    }

    @Test
    public void testInsertManyWithExistingImage() {
    }

    @Test
    public void testInsertManyWithNullImage() {
    }

    @Test
    public void testFindImage() {
    }

    @Test
    public void testFindNonExistentImage() {
    }

    @Test
    public void testFindInvalidImageId() {
    }

    @Test
    public void testFindNullImageId() {
    }

    @Test
    public void testUpdateImage() {
    }

    @Test
    public void testUpdateToExistingImageId() {
    }

    @Test
    public void testUpdateNullImageId() {
    }

    @Test
    public void testUpdateImageId() {
    }

    @Test
    public void testUpdateNonExistentImage() {
    }

    @Test
    public void testUpdateInvalidImageInputs() {
    }

    @Test
    public void testDeleteImage() {
    }

    @Test
    public void testDeleteNonExistentImage() {
    }

    @Test
    public void testDeleteInvalidImageId() {
    }
}