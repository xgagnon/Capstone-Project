package models;

import java.util.ArrayList;
import java.util.List;

import enums.Role;

public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private long phone;
    private String address;
    private transient String password;
    private Role role;
    private List<Image> cart = new ArrayList<>();
    private List<Image> likes = new ArrayList<>();
    private List<Transaction> transactionHistory = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Image> getCart() {
        return cart;
    }

    public void setCart(List<Image> cart) {
        this.cart = cart;
    }

    public List<Image> getLikes() {
        return likes;
    }

    public void setLikes(List<Image> likes) {
        this.likes = likes;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
