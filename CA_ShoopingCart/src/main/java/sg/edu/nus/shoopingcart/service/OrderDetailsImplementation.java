package sg.edu.nus.shoopingcart.service;
//Author xin min
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.shoopingcart.interfacemethods.OrderDetailsInterface;
import sg.edu.nus.shoopingcart.model.OrderDetails;
import sg.edu.nus.shoopingcart.repository.OrderDetailsRepository;


@Service
public class OrderDetailsImplementation implements OrderDetailsInterface {
	@Autowired
	OrderDetailsRepository orderDetailsRepo;
	
	@Transactional
	public void saveOrderDetails(OrderDetails orderDetails) {
		orderDetailsRepo.save(orderDetails);	
	}
	
	@Transactional
	public void saveAllOrderDetails(List<OrderDetails> orderDetailsList) {
		orderDetailsRepo.saveAll(orderDetailsList);	
	}
	
	@Transactional
    public List<OrderDetails> getOrderDetailsByOrderHistoryId(Long orderHistoryId) {
        return orderDetailsRepo.findByOrderHistoryId(orderHistoryId);
    }

}