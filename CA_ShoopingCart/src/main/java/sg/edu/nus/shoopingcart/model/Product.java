package sg.edu.nus.shoopingcart.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
//Author Pyae Phone Thant
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int product_id;
	@NotBlank(message="Product Name require")
	@Size(max=25,message="Product Name must be less than equal to 25 characters")
	private String product_name;
	
	private String product_description;
	
	@DecimalMin("500.00")@DecimalMax("5000.00")
	private double purchase_price;
	
	@DecimalMin("500.00")@DecimalMax("5000.00")
	private double sale_price;
	
	@Min(5)@Max(100)
	private int quantity;
	
	@NotBlank(message="Must upload at least 1 image for product")
	private String image_url;
	
	@NotBlank(message="Product feature require")
	private String product_feature;
	
	@OneToMany(mappedBy="product")
	private List<ProductReview> reviews;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private Customer createdBy;
	
	private LocalDateTime created_date;
	
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Customer updatedBy;
	
	private LocalDateTime updated_date;
	
	@NotNull(message="Brand must be selected")
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@NotNull(message="Brand must be selected")
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	private Boolean status;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public double getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct_feature() {
		return product_feature;
	}
	public void setProduct_feature(String product_feature) {
		this.product_feature = product_feature;
	}
	public List<ProductReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}
	public LocalDateTime getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(LocalDateTime updated_date) {
		this.updated_date = updated_date;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public Customer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Customer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Customer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Customer createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
