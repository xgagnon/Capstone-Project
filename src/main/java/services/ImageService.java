package services;
import dbaccess.ImageDB;
import models.Image;

import java.util.List;

public class ImageService {

    ImageDB imageDB = ImageDB.getInstance();

    public Image insert(Image image){
        imageDB.insert(image);
        return image;
    }
    public void insertMany(List<Image> images) {
        imageDB.insertMany(images);
    }

    public Image find(Long imageId){
        return imageDB.find(imageId);
    }

    public Image update(Image image){
        imageDB.update(image);
        return image;
    }

    public Image delete(Image image){
        imageDB.delete(image);
        return image;
    }

    public void validate(Image image){
        String path= image.getImageLocation();

        if(!path.contains(".jpg")||!path.contains(".png")||!path.contains(".jpeg")||!path.contains(".tiff")){
            throw new IllegalArgumentException("Unsupported Format!");
        }


    }
}
