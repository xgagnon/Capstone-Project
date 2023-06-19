package models;

import java.util.ArrayList;
import java.util.List;

public class Image {

    private int imageId;

    private String title;

    private String description;

    private String seller;

    private int likes;

    private int views;

    private double price;

    private String status;

    private String imageLocation;

    private List<Long> tags = new ArrayList<>();

    public int getImageId(){return imageId;}

    public void setImageId(int imageId){this.imageId=imageId;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title=title;}

    public String getDescription(){return description;}

    public void setDescription(String description){ this.description=description;}

    public String getSeller(){return seller;}

    public void setSeller(String seller){this.seller=seller;}

    public int getLikes(){return likes;}

    public void setLikes(int likes){this.likes=likes;}

    public int getViews(){return views;}

    public void setViews(int views){this.views=views;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price=price;}

    public String getStatus(){return status;}

    public void setStatus(String Status){ this.status=status;}

    public String getImageLocation(){return imageLocation;}

    public void setImageLocation(String imageLocation){this.imageLocation=imageLocation;}

    public List<Long> getTags() {return tags;}

    public void setTags(List<Long> tags) {this.tags = tags;}
}
