package sg.edu.nus.shoopingcart.service;

//Author Yuchen

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.model.PaymentMethod;

@Service
public class PaymentService {

    public List<PaymentMethod> getAllPaymentMethods() {
        return Arrays.asList(PaymentMethod.values());
    }

    public String getPaymentUrl(String paymentMethodDisplayName) {
        ArrayList<PaymentMethod> items = new ArrayList<>(Arrays.asList(PaymentMethod.values()));
        for (PaymentMethod method : items) {
            System.out.println("Payment Method: " + method.getDisplayName() + ", Image URL: " + method.getImageUrl());
        }
        for (PaymentMethod item : items) {
            if (item.getDisplayName() == null ? paymentMethodDisplayName == null : item.getDisplayName().equals(paymentMethodDisplayName)) {
                System.out.println(item.getDisplayName());
                System.out.println(paymentMethodDisplayName);
                return item.getImageUrl();
            }

        }
        return null;
    }
}
