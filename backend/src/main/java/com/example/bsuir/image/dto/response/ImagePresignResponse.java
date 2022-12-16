package com.example.bsuir.image.dto.response;

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
public class ImagePresignResponse implements Response<Image> {

    private String url;

    private String keyName;
}
