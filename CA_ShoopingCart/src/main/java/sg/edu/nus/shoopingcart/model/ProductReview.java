package sg.edu.nus.shoopingcart.model;
//Author Pyae Phone Thant

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="productreview")
public class ProductReview {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/*
	 * private int product_id; private long customer_id;
	 */
	@NotBlank(message="Review Title is required")
	@Size(max=25,message="Title must not be > 25 characters")
	private String review_title;
	@NotBlank(message="Review Text is required")
	@Size(max=255,message="Title must not be > 255 characters")
	private String review_text;
	private int rating;
	private LocalDateTime created_date;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	/*
	 * public long getCustomer_id() { return customer_id; } public void
	 * setCustomer_id(long customer_id) { this.customer_id = customer_id; }
	 */

	/*
	 * public int getProduct_id() { return product_id; } public void
	 * setProduct_id(int product_id) { this.product_id = product_id; }
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
