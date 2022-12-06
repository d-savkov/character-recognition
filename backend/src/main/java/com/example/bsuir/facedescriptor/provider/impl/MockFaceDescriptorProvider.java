package com.example.bsuir.facedescriptor.provider.impl;

import com.example.bsuir.facedescriptor.provider.FaceDescriptorProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "project.face.descriptor.provider", havingValue = "mock")
public class MockFaceDescriptorProvider implements FaceDescriptorProvider {

    @Override
    public Map<Integer, Double> get(MultipartFile multipartFile) {
        Map<Integer, Double> result = new HashMap<>(128);
        for (int i = 0; i < 128; i++) {
            result.put(i, Math.random());
        }
        return result;
    }
}
