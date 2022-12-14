package com.example.bsuir.image.dto;

import com.example.bsuir.image.model.Image;
import com.example.bsuir.shared.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse implements Response<Image> {

    private Long id;

    private String keyName;

    private String url;

    private Long characterId;
}
