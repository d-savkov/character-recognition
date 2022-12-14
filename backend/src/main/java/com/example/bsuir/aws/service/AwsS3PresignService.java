package com.example.bsuir.aws.service;

public interface AwsS3PresignService {

    String getPresignUploadUrl(String keyName);

    String getPresignDownloadUrl(String keyName);
}
