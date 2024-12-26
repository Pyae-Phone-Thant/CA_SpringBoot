package sg.edu.nus.shoopingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.edu.nus.shoopingcart.interceptor.SecurityInterceptor;

// Author: Siow Xiang Ying
@Component
public class WebAppConfig implements WebMvcConfigurer {
  @Autowired
  SecurityInterceptor securityInterceptor;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // This interceptor only takes care of the URL paths stated below
    registry.addInterceptor(securityInterceptor)
            .addPathPatterns("/cart", "/cart/*","/checkout","/checkout/*","/product-review/*","/view-profile","/change-password","/admin/**");

  }
}
