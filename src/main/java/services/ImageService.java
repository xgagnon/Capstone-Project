package services;
import models.Image;

public class ImageService {

    public void insertMany(){

    }

    public void validate(Image image){
        String path= image.getImageLocation();

        if(!path.contains(".jpg")||!path.contains(".png")||!path.contains(".jpeg")||!path.contains(".tiff")){
            throw new IllegalArgumentException("Unsupported Format!");
        }


    }
}
