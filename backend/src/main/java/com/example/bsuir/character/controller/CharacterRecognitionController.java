package com.example.bsuir.character.controller;

import com.example.bsuir.character.dto.response.CharacterSimilarityResponse;
import com.example.bsuir.character.service.CharacterRecognitionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/face-recognition")
@AllArgsConstructor
public class CharacterRecognitionController {

    private final CharacterRecognitionService service;

    @PostMapping("/most-similar")
    public List<CharacterSimilarityResponse> findMostSimilar(@RequestBody MultipartFile file) {
        return service.findMostSimilar(file);
    }

    @PostMapping("/similarity")
    public List<CharacterSimilarityResponse> getAll(@RequestBody MultipartFile file) {
        return service.getAll(file);
    }
}
