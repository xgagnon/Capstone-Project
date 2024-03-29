package models;

import enums.Tags;

import java.util.ArrayList;
import java.util.List;

public class Image {

    private int imageId;

    private String title;

    private String description;

    private int seller;

    private int likes;

    private int views;

    private double price;

    private String status;

    private String imageLocation;

    private ArrayList<Tags> tags = new ArrayList<>();

    public int getImageId(){return imageId;}

    public void setImageId(int imageId){this.imageId=imageId;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title=title;}

    public String getDescription(){return description;}

    public void setDescription(String description){ this.description=description;}

    public int getSeller(){return seller;}

    public void setSeller(int seller){this.seller=seller;}

    public int getLikes(){return likes;}

    public void setLikes(int likes){this.likes=likes;}

    public int getViews(){return views;}

    public void setViews(int views){this.views=views;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price=price;}

    public String getStatus(){return status;}

    public void setStatus(String status){ this.status=status;}

    public String getImageLocation(){return imageLocation;}

    public void setImageLocation(String imageLocation){this.imageLocation=imageLocation;}

    public ArrayList<Tags> getTags() {return tags;}

    public void setTags(ArrayList<Tags> tags) {this.tags = tags;}
}
