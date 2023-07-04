package validation;

import javax.validation.constraints.*;
import java.util.List;

public class UserValidation {

    @NotNull
    @Min(value = 1000000, message = "User ID must be a 7-digit number") // 7 digits
    @Max(value = 9999999, message = "User ID must be a 7-digit number") // 7 digits
    private Integer userId;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Min(value = 1000000000L, message = "Phone number must be a 10-digit number")
    @Max(value = 9999999999L, message = "Phone number must be a 10-digit number")
    private Long phone;

    @NotBlank
    private String address;

    @NotBlank
    private String password;

    @NotEmpty
    private List<String> role;

    @NotBlank
    private String status;

    @NotEmpty
    private List<Long> cart;

    @NotEmpty
    private List<Long> likes;

    @NotEmpty
    private List<Long> transaction;

    // Getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
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

    public List<Long> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Long> transaction) {
        this.transaction = transaction;
    }
}