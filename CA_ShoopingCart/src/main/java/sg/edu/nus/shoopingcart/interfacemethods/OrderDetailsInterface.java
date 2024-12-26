package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

//Author: Ong Xin Min

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.shoopingcart.model.Customer;
import sg.edu.nus.shoopingcart.model.OrderDetails;
import sg.edu.nus.shoopingcart.repository.OrderDetailsRepository;

public interface OrderDetailsInterface {

	
	public void saveOrderDetails(OrderDetails orderDetails);
	public void saveAllOrderDetails(List<OrderDetails> orderDetailsList);
	
	public List<OrderDetails> getOrderDetailsByOrderHistoryId(Long orderHistoryId);
}
