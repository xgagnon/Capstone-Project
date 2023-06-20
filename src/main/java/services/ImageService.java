package services;


import dbaccess.ImageDB;
import exceptions.ImageException;
import models.Image;

import java.util.List;

public class ImageService {
    ImageDB imageDb = ImageDB.getInstance();

    public void insert(Image image) throws ImageException {

        if(imageDb.find(image.getImageId()) != null) {
            throw new ImageException("An image with the ID " +image.getImageId()+ " already exists");
        }
        imageDb.insert(image);
    }

    public void insertMany(List<Image> images) throws ImageException {

        for(Image image: images) {
            if(imageDb.find(image.getImageId()) != null) {
                throw new ImageException("An image with the ID " +image.getImageId()+ " already exists");
            }
        }
        imageDb.insertMany(images);
    }

    public Image find(int imageId) {
        return imageDb.find(imageId);
    }

    public void update(Image image) {
        imageDb.update(image);
    }

    public void delete(Image image) { imageDb.delete(image);
    }
}
