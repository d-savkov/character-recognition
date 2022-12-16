package com.example.bsuir.aws.service.impl;

import com.example.bsuir.aws.service.AwsS3PresignService;
import com.example.bsuir.config.property.AwsProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class AwsS3PresignServiceImpl implements AwsS3PresignService {

    private final AwsProperty awsProperty;
    private final S3Presigner s3Presigner;

    @Override
    public String getPresignUploadUrl(String keyName) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(awsProperty.getS3().getBucketName())
                .key(keyName)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(awsProperty.getS3().getPresignedUrlExpirationDuration())
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        return presignedRequest.url().toString();
    }

    @Override
    public String getPresignDownloadUrl(String keyName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(awsProperty.getS3().getBucketName())
                .key(keyName)
                .build();

        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(awsProperty.getS3().getPresignedUrlExpirationDuration())
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);

        return presignedGetObjectRequest.url().toString();
    }
}
