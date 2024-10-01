package sg.edu.nus.shoopingcart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class ShoopingCartController {
	@GetMapping("/hello")  	 
	public String hello() {
	        System.out.println("hello"); 	        
	        return "Hello World!";
	    }

}
