package com.example.bsuir.person.service;

import com.example.bsuir.person.dto.PersonSimilarityDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonRecognitionService {

    List<PersonSimilarityDto> findMostSimilar(MultipartFile file);

    List<PersonSimilarityDto> getAll(MultipartFile file);
}
