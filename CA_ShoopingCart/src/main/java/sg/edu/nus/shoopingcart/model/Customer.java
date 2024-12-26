package sg.edu.nus.shoopingcart.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
// Author: Yuchen, Yichi, Rongshu
@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 16, message = "Username must be between 2 and 16 characters")
	@Column(name = "name")
	private String username;

	@NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank(message = "Address cannot be empty")
	@Column(name = "address")
	private String address;
	@Column(name = "role", length = 10)
	private String role;
	@OneToMany(mappedBy="customer")
	private List<ProductReview> reviews;
	
	@OneToMany(mappedBy="createdBy")
	private List<Product> createdProducts;
	
	@OneToMany(mappedBy="updatedBy")
	private List<Product> updatedProdutcs;
	@Column(name = "reset_token")
	private String resetToken;
	public Customer() {
        super();
    }

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	public String getEmail(){
		return this.email;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public String getAddress(){
		return this.address;
	}

	public void setUsername(String name){
		this.username = name;
	}

	public void setPassword(String word){
		this.password = word;
	}

	public void setEmail(String mail){
		this.email = mail;
	}

	public void setPhoneNumber(String phone){
		this.phoneNumber = phone;
	}

	public void setAddress(String addr){
		this.address = addr;
	}

	public List<ProductReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Product> getUpdatedProdutcs() {
		return updatedProdutcs;
	}

	public void setUpdatedProdutcs(List<Product> updatedProdutcs) {
		this.updatedProdutcs = updatedProdutcs;
	}

	public List<Product> getCreatedProducts() {
		return createdProducts;
	}

	public void setCreatedProducts(List<Product> createdProducts) {
		this.createdProducts = createdProducts;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
		
	}
}
