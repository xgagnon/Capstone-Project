package models;

import java.util.ArrayList;
import java.util.List;

import enums.Role;

public class User {
    private int userId;
    private String uid;
    private transient String password;
    private String email;
    private String firstName;
    private String lastName;
    private long phone;
    private String address;
    private Role role;
    private String status;
    private List<Long> cart = new ArrayList<>();
    private List<Long> likes = new ArrayList<>();
    private List<Long> transactions = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getCart() {
        return cart;
    }

    public void setCart(List<Long> cart) {
        this.cart = cart;
    }

    public List<Long> getLikes() {
        return likes;
    }

    public void setLikes(List<Long> likes) {
        this.likes = likes;
    }

    public List<Long> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Long> transactions) {
        this.transactions = transactions;
    }
}
