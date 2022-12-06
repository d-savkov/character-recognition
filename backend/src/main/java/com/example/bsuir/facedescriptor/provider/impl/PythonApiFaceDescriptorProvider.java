package com.example.bsuir.facedescriptor.provider.impl;

import com.example.bsuir.facedescriptor.provider.FaceDescriptorProvider;
import com.example.bsuir.rest.HttpRequestSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@ConditionalOnProperty(name = "project.face.descriptor.provider", havingValue = "python")
public class PythonApiFaceDescriptorProvider implements FaceDescriptorProvider {

    private final HttpRequestSender requestSender;
    private final ObjectMapper mapper;

    @Override
    public Map<Integer, Double> get(MultipartFile multipartFile) {
        try {
            multipartFile.getContentType();
            requestSender.send(multipartFile);
            String response = requestSender.getResponse();
            return transformResponse(response);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Can't process file", exception);
        }
    }

    private Map<Integer, Double> transformResponse(String response) throws JsonProcessingException {
        Map<Integer, Double> result = new HashMap<>();
        Map<String, Double> map = (Map<String, Double>) mapper.readValue(response, Map.class);
        map.forEach((key, value) -> result.put(Integer.valueOf(key), value));
        return result;
    }
}
