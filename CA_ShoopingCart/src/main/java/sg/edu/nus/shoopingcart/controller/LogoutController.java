package sg.edu.nus.shoopingcart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST, RequestMethod.OPTIONS}) // Allow POST and OPTIONS
public class LogoutController {

    //Author: Siow Xiang Ying
	@PostMapping("/ordershistorylogout")
	public ResponseEntity<String> ordersHistoryLogout(HttpSession sessionObj){
		sessionObj.invalidate();
		return ResponseEntity.ok("Logout sucessful");
	}
}
