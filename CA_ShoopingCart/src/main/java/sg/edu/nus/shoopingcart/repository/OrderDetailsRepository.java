package sg.edu.nus.shoopingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.OrderDetails;
import sg.edu.nus.shoopingcart.model.OrderDetailsId;
//Author Xin Min
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
	 
	@Query("SELECT od FROM OrderDetails od WHERE od.orderHistoryId = :orderHistoryId")
	public List<OrderDetails> findByOrderHistoryId(@Param("orderHistoryId") Long orderHistoryId); // Custom query method
}