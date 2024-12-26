package sg.edu.nus.shoopingcart.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//Aurhor Pyae Phone Thant
@Entity
@Table
public class Brand implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int brand_id;
	@Column(length=25,nullable=false)
	@NotBlank(message="Brand name must be enter")
	@Size(max=25,message="Brand name must be between 25 characters")
	private String brand_name;
	private String brand_description;
	@ManyToOne
	@JoinColumn(name="created_by")
	private Customer createdBy;
	private LocalDateTime created_date;
	@Column(nullable=false,columnDefinition="bit default 1")
	private Boolean status;
	@OneToMany(mappedBy="brand")
	private List<Product> products;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public Customer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Customer createdBy) {
		this.createdBy = createdBy;
	}
	public String getBrand_description() {
		return brand_description;
	}
	public void setBrand_description(String category_description) {
		this.brand_description = category_description;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String category_name) {
		this.brand_name = category_name;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int category_id) {
		this.brand_id = category_id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
