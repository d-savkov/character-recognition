package com.example.bsuir.character.service;

import com.example.bsuir.character.dto.response.CharacterSimilarityResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CharacterRecognitionService {

    List<CharacterSimilarityResponse> findMostSimilar(MultipartFile file);

    List<CharacterSimilarityResponse> getAll(MultipartFile file);
}
