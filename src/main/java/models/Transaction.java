package models;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private int transactionId;
    private String date;

    private double price;

    private String buyerEmail;

    private  String status;

    private List<Long> purchasedImages = new ArrayList<>();

    List <Long> images= new ArrayList<>();

    public int getTransactionId() {return transactionId;}

    public void setTransactionId(int transactionId) {this.transactionId = transactionId;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getBuyerEmail() {return buyerEmail;}

    public void setBuyerEmail(String buyerEmail) {this.buyerEmail = buyerEmail;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public List<Long> getPurcahsedImages() {return purchasedImages;}
}
