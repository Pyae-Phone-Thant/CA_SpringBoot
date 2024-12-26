package sg.edu.nus.shoopingcart.service;

//Author Yuchen, Rongshu, Yichi
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.CustomerInterface;
import sg.edu.nus.shoopingcart.model.Customer;
import sg.edu.nus.shoopingcart.repository.CustomerRepository;

@Service
public class CustomerImplementation implements CustomerInterface {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private EncryptDecryptImplementation encryptDecryptService;
	
    @Override
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
    @Override
	public Customer getCustomerByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
    @Override
	public Customer saveCustomer(Customer customer) {
    	customer.setPassword(encryptDecryptService.encrypt(customer.getPassword()));
		return customerRepo.save(customer);
	}
    @Override
    public Customer getCustomerById(Long id) {
    	return customerRepo.findByCustomerId(id);
    }
    @Override
    public boolean existsByEmail(String email) {
    	return customerRepo.existsByEmail(email);
    }
    @Override
    public long getTotalCustomersCount() {
    	return customerRepo.count();
    }
    @Override
    public String changeUserPassword(long userId, String oldPassword, String newPassword, String newPasswordRepeat) {
        Customer customer = getCustomerById(userId);
        String encryptedPassword = encryptDecryptService.encrypt(oldPassword);

        if (!encryptedPassword.equals(customer.getPassword())) {
            return "The password is incorrect!";
        }

        if (newPassword == null || newPassword.length() < 6) {
            return "New password must be at least 6 characters!";
        }

        if (oldPassword.equals(newPassword)) {
            return "New password cannot be the same as the old one!";
        }

        if (!newPassword.equals(newPasswordRepeat)) {
            return "New passwords do not match!";
        }

        customer.setPassword(newPassword);
        saveCustomer(customer);
        return null; // Success, no error message
    }
    @Override
    public boolean verifyResetToken(String email, String token) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer != null && "111111".equals(customer.getResetToken())) {
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(Customer customer, String newPassword) {
    	customer.setPassword(encryptDecryptService.encrypt(newPassword));
        customer.setResetToken(null);
        customerRepo.save(customer);
    }
    @Override
    public String updateUserProfile(long userId, String email, String username, String phoneNumber, String address) {
        Customer customer = getCustomerById(userId);
        
        // Check if email is used by another customer
        if (existsByEmail(email) && !customer.getEmail().equals(email)) {
            return "The email is already in use!";
        }
        customer.setPassword(encryptDecryptService.decrypt(customer.getPassword()));
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        saveCustomer(customer);
        return null; // Success, no error message
    }
}
