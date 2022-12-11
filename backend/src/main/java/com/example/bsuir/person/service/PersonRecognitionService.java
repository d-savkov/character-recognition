package com.example.bsuir.person.service;

import com.example.bsuir.person.dto.response.PersonSimilarityResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonRecognitionService {

    List<PersonSimilarityResponse> findMostSimilar(MultipartFile file);

    List<PersonSimilarityResponse> getAll(MultipartFile file);
}
