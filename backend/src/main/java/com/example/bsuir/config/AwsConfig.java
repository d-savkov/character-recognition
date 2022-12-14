package com.example.bsuir.config;

import com.example.bsuir.config.property.AwsProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final AwsProperty awsProperty;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return EnvironmentVariableCredentialsProvider.create();
    }

    @Bean
    public S3Presigner s3Presigner(AwsCredentialsProvider credentialsProvider) {
        return S3Presigner.builder()
                          .credentialsProvider(credentialsProvider)
                          .region(awsProperty.getDefaultRegion())
                          .build();
    }
}
