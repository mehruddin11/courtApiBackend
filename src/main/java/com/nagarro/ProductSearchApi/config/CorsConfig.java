package com.nagarro.ProductSearchApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer { 
	
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
    		.allowedOrigins("https://courtapptest.azurewebsites.net", "https://startling-malasada-fb9e54.netlify.app","http://localhost:4200")
            .allowedMethods("*")
            .allowedHeaders("*");
    }

}
