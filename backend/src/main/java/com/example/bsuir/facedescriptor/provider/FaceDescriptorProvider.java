package com.example.bsuir.facedescriptor.provider;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FaceDescriptorProvider {

    Map<Integer, Double> get(MultipartFile multipartFile);
}
