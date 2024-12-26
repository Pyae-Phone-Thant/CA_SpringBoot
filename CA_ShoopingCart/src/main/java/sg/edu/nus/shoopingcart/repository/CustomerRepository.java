package sg.edu.nus.shoopingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Customer;
//Author: Yuchen, Yichi, Rongshu
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	 Customer findByEmail(String email);
	 
	 @Query("select c from Customer as c where c.customerId=:id")
	 public Customer findByCustomerId(@Param("id")Long id);
	 boolean existsByEmail(String email);
}
