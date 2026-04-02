package com.booknest.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all backend endpoints
                        .allowedOrigins("http://localhost:3002") // Allow this frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Allowed HTTP methods
            }
        };
    }
}