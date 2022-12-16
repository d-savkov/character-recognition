package com.example.bsuir.image.service.impl;

import com.example.bsuir.aws.service.AwsS3PresignService;
import com.example.bsuir.image.dto.response.ImagePresignResponse;
import com.example.bsuir.image.generator.KeyNameGenerator;
import com.example.bsuir.image.service.ImagePresignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagePresignServiceImpl implements ImagePresignService {

    private final AwsS3PresignService awsS3PresignService;
    private final KeyNameGenerator keyNameGenerator;

    @Override
    public ImagePresignResponse getPresignResponse(Long showId, Long characterId, String fileType) {
        String keyName = keyNameGenerator.generate(showId, characterId, fileType);
        String url = awsS3PresignService.getPresignUploadUrl(keyName);
        return new ImagePresignResponse(url, keyName);
    }
}
