package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.OrderHistory;

//Author: Ong Xin Min
public interface OrderHistoryInterface {
	public Long getOrderHistoryId(Long userId);
	public void saveOrder(OrderHistory orderHistory);
	public OrderHistory getOrderHistory(Long userId);
	public long getTotalOrdersCount();
}
