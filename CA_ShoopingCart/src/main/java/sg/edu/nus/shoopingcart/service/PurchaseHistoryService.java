package sg.edu.nus.shoopingcart.service;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.model.OrderDetails;
import sg.edu.nus.shoopingcart.model.OrderHistory;
import sg.edu.nus.shoopingcart.model.PurchaseHistoryResponse;
import sg.edu.nus.shoopingcart.model.PurchaseItemResponse;
import sg.edu.nus.shoopingcart.repository.OrderHistoryRepository;

//Author: Siow Xiang Ying
@Service
public class PurchaseHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    /**
     * Fetches all order histories for customer and maps them to custom response objects.
     * returns JSON file to react
     */
    public List<PurchaseHistoryResponse> getOrderHistoriesOfCustWithTotal(Long userId) {
        
        List<OrderHistory> orderHistories = orderHistoryRepository.findOrderHistoryByCustomerId(userId);

        return orderHistories.stream().map(order -> {
            // Calculate the total for the order
            double totalValue = calculateTotal(order);

            // Check if the customer object is null
            String customerUsername = order.getCustomer() != null ? order.getCustomer().getUsername() : "Unknown";
            String customerAddress = order.getCustomer() != null ? order.getCustomer().getAddress() : "No Address";

                // Format the date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                String formattedDate = order.getOrderDate().format(formatter);


            // Map order details to custom item response objects
            List<PurchaseItemResponse> items = order.getOrderDetails().stream().map(this::mapToPurchaseItemResponse).collect(Collectors.toList());

            // Create and return the custom OrderHistoryResponse object
            return new PurchaseHistoryResponse(
                    order.getOrderId().toString(),
                    formattedDate, // You can format date as needed
                    formatCurrency(totalValue),
                    customerUsername,
                    customerAddress,
                    order.getPaymentMethod(),
                    items
            );
        }).collect(Collectors.toList());
    }

    /**
     * Maps OrderDetail to OrderItemResponse.
     */
    private PurchaseItemResponse mapToPurchaseItemResponse(OrderDetails orderDetails) {
        // Assume the itemId is the product ID in this context
        Long itemId = (long) orderDetails.getProduct().getProduct_id();

        // Clean the image URL using the newly created method
        String cleanedImageUrl = cleanImageUrl(orderDetails.getProduct().getImage_url());

        return new PurchaseItemResponse(
                itemId,
                orderDetails.getProduct().getProduct_name(),
                cleanedImageUrl,
                "Delivered", // Assuming status is always 'Delivered' in this project.
                formatCurrency(orderDetails.getSalePrice()),
                orderDetails.getQuantity()
        );
    }

    /**
     * Calculates total for a given order.
     */
    private double calculateTotal(OrderHistory orderHistory) {
        return orderHistory.getOrderDetails().stream()
                .mapToDouble(orderDetail -> orderDetail.getSalePrice() * orderDetail.getQuantity())
                .sum();
    }

    /**
     * Formats the value as currency.
     */
    private String formatCurrency(double value) {
        DecimalFormat formatter = new DecimalFormat("S$#.00");
        return formatter.format(value);
    }

    // To clean Image Url
    private String cleanImageUrl(String imageUrl){
        if(imageUrl == null || imageUrl.isEmpty()){
            return "No Image";
        }
        // Split the image URL by commas
        String[] imageNames = imageUrl.split(",");

        // Take the first image
        String firstImageName = imageNames[0];

        //Split by _ and take the first part
        String[] firstImageWOextension = firstImageName.split("_");
        String firstImage = firstImageWOextension[0];

        firstImage = firstImage.replaceAll("\\s", "");

        return firstImage;
    }

}