package validation;


import javax.validation.constraints.*;
import java.util.List;

public class TransactionValidation {

    @NotNull
    @Min(value = 1000000000L, message = "Transaction ID must be a 10-digit number.")// 10 digits
    @Max(value = 9999999999L, message = "Transaction ID must be a 10-digit number.")// 10 digits
    private Long transactionId;

    @NotBlank
    private String date;

    @NotNull
    @DecimalMin(value = "0.0", message = "Price must be a positive number.")
    private Double price;

    @NotEmpty
    private List<String> purchasedImages;

    @NotBlank
    @Email(message = "Invalid buyer email.")
    private String buyerEmail;

    @NotBlank
    private String status;

    // Getters and setters

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getPurchasedImages() {
        return purchasedImages;
    }

    public void setPurchasedImages(List<String> purchasedImages) {
        this.purchasedImages = purchasedImages;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}