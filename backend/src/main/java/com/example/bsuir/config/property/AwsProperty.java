package com.example.bsuir.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import software.amazon.awssdk.regions.Region;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties("aws")
public class AwsProperty {
    private Region defaultRegion;
    private S3Config s3;

    @Getter
    @Setter
    public static class S3Config {
        private String bucketName;
        private Integer presignedUrlExpiration;

        public Duration getPresignedUrlExpirationDuration() {
            return Duration.ofMinutes(presignedUrlExpiration);
        }
    }
}
