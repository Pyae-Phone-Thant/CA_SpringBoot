package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.Customer;

//Author: Yuchen, Yichi, RongShu
public interface CustomerInterface {
	public List<Customer> getAllCustomers();
	public Customer getCustomerByEmail(String email);
	public Customer saveCustomer(Customer customer);
	public Customer getCustomerById(Long id);
	public boolean existsByEmail(String email);
	public long getTotalCustomersCount();
	public String updateUserProfile(long userId, String email, String username, String phoneNumber, String address);
	public String changeUserPassword(long userId, String oldPassword, String newPassword, String newPasswordRepeat);
	public void updatePassword(Customer customer, String newPassword);
	public boolean verifyResetToken(String email, String token);
}
