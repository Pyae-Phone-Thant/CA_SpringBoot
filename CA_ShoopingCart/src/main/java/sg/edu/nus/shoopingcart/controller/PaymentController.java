package sg.edu.nus.shoopingcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpServletRequest;
import sg.edu.nus.shoopingcart.config.PaypalPaymentIntent;
import sg.edu.nus.shoopingcart.config.PaypalPaymentMethod;
import sg.edu.nus.shoopingcart.service.PaypalService;
import sg.edu.nus.shoopingcart.utils.URLUtils;

//Author: Yuchen
@Controller
@RequestMapping("/checkout/paypal")
public class PaymentController {

    public static final String PAYPAL_SUCCESS_URL = "/success";
    public static final String PAYPAL_CANCEL_URL = "/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "checkout";
    }

    /**
     * start payment
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/pay")
    public String pay(HttpServletRequest request){
        String baseUrl = URLUtils.getBaseURl(request) + "/checkout/paypal";
        String cancelUrl = baseUrl + PAYPAL_CANCEL_URL;
        String successUrl = baseUrl + PAYPAL_SUCCESS_URL;
        
        log.info("Cancel URL: " + cancelUrl);
        log.info("Success URL: " + successUrl);

        try {
            Payment payment = paypalService.createPayment(
                    CheckOutController.totalPrice, "SGD", 
                    PaypalPaymentMethod.paypal, 
                    PaypalPaymentIntent.sale,
                    "payment description", cancelUrl, successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * 交易取消
     * @return
     */@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }
     
    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "redirect:/checkout/success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
