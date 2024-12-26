package sg.edu.nus.shoopingcart.model;

import java.util.List;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//Author: Ong Xin Min

@Entity
@Table(name="OrderHistory")
public class OrderHistory {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id",nullable=false)
    private Customer customer;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    //OneToMany relationship with OrderDetails
    @OneToMany(mappedBy="orderHistory", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails; //Use List or Set based on your requirement
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/*
	 * public Long getCustomerId() { return customerId; } public void
	 * setCustomerId(Long customerId) { this.customerId = customerId; }
	 */
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

