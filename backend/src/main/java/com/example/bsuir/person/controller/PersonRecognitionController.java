package com.example.bsuir.person.controller;

import com.example.bsuir.person.dto.PersonSimilarityDto;
import com.example.bsuir.person.service.PersonRecognitionService;
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
public class PersonRecognitionController {

    private final PersonRecognitionService personRecognitionService;

    @PostMapping("/most-similar")
    public List<PersonSimilarityDto> findMostSimilar(@RequestBody MultipartFile file) {
        return personRecognitionService.findMostSimilar(file);
    }

    @PostMapping("/similarity")
    public List<PersonSimilarityDto> getAll(@RequestBody MultipartFile file) {
        return personRecognitionService.getAll(file);
    }
}
