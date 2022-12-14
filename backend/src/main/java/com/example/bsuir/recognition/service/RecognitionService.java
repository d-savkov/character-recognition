package com.example.bsuir.recognition.service;

import com.example.bsuir.recognition.dto.response.RecongitionResponse;

import java.util.List;
import java.util.Map;

public interface RecognitionService {

    List<RecongitionResponse> getMostSimilar(Map<Integer, Double> descriptor);

    List<RecongitionResponse> getAll(Map<Integer, Double> descriptor);
}
