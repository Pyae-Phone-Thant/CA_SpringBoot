//Author: Xin Min
package sg.edu.nus.shoopingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	// @Query("SELECT oh.orderId FROM OrderHistory oh WHERE oh.customerId = :userId")
	@Query("SELECT oh.orderId FROM OrderHistory oh WHERE oh.orderDate = (SELECT MAX(o.orderDate) FROM OrderHistory o WHERE o.customer.customerId = :userId) AND oh.customer.customerId = :userId")
	public Long findLatestOrderHistoryId(@Param("userId") Long UserId); // Custom query method

	@Query("SELECT oh FROM OrderHistory oh WHERE oh.orderDate = (SELECT MAX(o.orderDate) FROM OrderHistory o WHERE o.customer.customerId = :userId) AND oh.customer.customerId = :userId")
	public OrderHistory findLatestOrderHistory(@Param("userId") Long UserId); // Custom query method

	// Author: Siow Xiang Ying
	@Query("SELECT oh FROM OrderHistory oh WHERE oh.customer.customerId = :userId")
	public List<OrderHistory> findOrderHistoryByCustomerId(@Param("userId") Long UserId);
}