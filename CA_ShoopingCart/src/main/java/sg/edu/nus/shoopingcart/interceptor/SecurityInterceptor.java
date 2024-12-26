package sg.edu.nus.shoopingcart.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Author: Siow Xiang Ying
@Component
public class SecurityInterceptor implements HandlerInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, Object handler) throws IOException {

    LOGGER.info("Intercepting: " + request.getRequestURI());
    
    HttpSession session = request.getSession();
    Long username = (Long) session.getAttribute("userId");
                            
    if (username == null) {
        // No username, meaning not logged in yet
        // Redirect to login page
    	if(request.getRequestURI().contains("/cart")) {
    		session.setAttribute("productId", request.getParameter("productId"));
    	}
    	session.setAttribute("redirectToLoginFrom", request.getRequestURI());
        response.sendRedirect("/login");
        return false;
      }
      //Aurthor Pyae Phone Thant
    String role=(String) session.getAttribute("userRole");
     if(request.getRequestURI().contains("admin")&&!role.equals("user"))  {
    	 response.sendRedirect("/access-denied");
         return false;
     }
      // Have logged-in, forward to Controller
      return true;
    }
  }