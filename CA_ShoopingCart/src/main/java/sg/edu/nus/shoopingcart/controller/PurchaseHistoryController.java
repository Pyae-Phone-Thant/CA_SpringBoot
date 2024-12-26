package sg.edu.nus.shoopingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.shoopingcart.model.PurchaseHistoryResponse;
import sg.edu.nus.shoopingcart.service.PurchaseHistoryService;

//Author: Siow Xiang Ying
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/order-history")
public class PurchaseHistoryController {

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    //Endpoint to get all orders in the desired JSON format
    @GetMapping
    public ResponseEntity<List<PurchaseHistoryResponse>> getAllPurchaseHistories(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        List<PurchaseHistoryResponse> purchaseHistoryResponses = purchaseHistoryService.getOrderHistoriesOfCustWithTotal(userId);
        return ResponseEntity.ok(purchaseHistoryResponses);
    }

}
