package sg.edu.nus.shoopingcart.model;
//Author: Ong Xin Min
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class PaymentMethodEntity {

    private Long id; // Unique identifier for the payment method

    @Enumerated(EnumType.STRING)
    private PaymentMethod methodName; // Use enum here

    private String details; // Additional details (if any)

    private String imageUrl;

// Constructors
    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(PaymentMethod methodName, String details) {
        this.methodName = methodName;
        this.details = details;
    }

// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getMethodName() {
        return methodName;
    }

    public void setMethodName(PaymentMethod methodName) {
        this.methodName = methodName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
