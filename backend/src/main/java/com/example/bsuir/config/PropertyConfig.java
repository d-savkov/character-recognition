package com.example.bsuir.config;

import com.example.bsuir.config.property.AwsProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Bean
    public AwsProperty awsProperty() {
        return new AwsProperty();
    }
}
