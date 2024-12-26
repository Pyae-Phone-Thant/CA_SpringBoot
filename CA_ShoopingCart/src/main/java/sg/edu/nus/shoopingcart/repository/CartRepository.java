package sg.edu.nus.shoopingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Cart;

//Author: Siow Xiang Ying
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    Cart findByCustomerId(@Param("customerId") long customerId);
}
