package com.example.bsuir.image.dto.request;

import com.example.bsuir.image.model.Image;
import com.example.bsuir.shared.dto.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateImageRequest implements Request<Image> {

    private String keyName;

    private List<Double> faceDescriptor;

    private Long characterId;
}
