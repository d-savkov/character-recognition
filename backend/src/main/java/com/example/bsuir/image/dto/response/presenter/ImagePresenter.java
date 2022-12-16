package com.example.bsuir.image.dto.response.presenter;

import com.example.bsuir.aws.service.AwsS3PresignService;
import com.example.bsuir.image.dto.response.ImageResponse;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagePresenter implements Presenter<Image, ImageResponse> {

    private final AwsS3PresignService presignService;

    @Override
    public ImageResponse toDto(Image entity) {
        return new ImageResponse(
                entity.getId(),
                entity.getKeyName(),
                presignService.getPresignDownloadUrl(entity.getKeyName()),
                entity.getCharacter().getId()
        );
    }
}
