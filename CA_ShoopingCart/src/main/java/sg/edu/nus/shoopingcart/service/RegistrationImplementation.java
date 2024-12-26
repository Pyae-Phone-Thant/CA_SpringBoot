package sg.edu.nus.shoopingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.shoopingcart.model.Customer;

//Author: Siow Xiang Ying
@Service
public class RegistrationImplementation {

     @Autowired
    private CustomerImplementation customerService;

    @Autowired
    private CartImplementation cartService;

    @Transactional
    public Customer registerCustomerAndCreateCart(Customer customer) {
        // Save the new customer
        Customer savedCustomer = customerService.saveCustomer(customer);

        // Create a new cart for the registered customer
        cartService.createCart(savedCustomer.getCustomerId());

        return savedCustomer;
    }
}
