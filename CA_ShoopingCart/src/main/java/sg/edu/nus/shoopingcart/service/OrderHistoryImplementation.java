package sg.edu.nus.shoopingcart.service;
//Author xin min
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.edu.nus.shoopingcart.interfacemethods.OrderHistoryInterface;
import sg.edu.nus.shoopingcart.model.OrderHistory;
import sg.edu.nus.shoopingcart.repository.OrderHistoryRepository;


@Service
public class OrderHistoryImplementation implements OrderHistoryInterface {
	@Autowired
	OrderHistoryRepository orderHistoryRepo;
	
	@Transactional
	public Long getOrderHistoryId(Long userId) {
		return orderHistoryRepo.findLatestOrderHistoryId(userId);
	}
	
	@Transactional
	public void saveOrder(OrderHistory orderHistory) {
		orderHistoryRepo.save(orderHistory);	
	}
	@Transactional
	public OrderHistory getOrderHistory(Long userId) {
		return orderHistoryRepo.findLatestOrderHistory(userId);
	}
	public long getTotalOrdersCount() {
		return orderHistoryRepo.count();
	}
}
