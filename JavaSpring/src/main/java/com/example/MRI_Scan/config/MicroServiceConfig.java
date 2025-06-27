package com.example.MRI_Scan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MicroServiceConfig {
@Bean
    public RestTemplate restTemplate() {
    return new RestTemplate();
}
}
