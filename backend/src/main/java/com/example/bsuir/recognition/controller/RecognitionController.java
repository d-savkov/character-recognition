package com.example.bsuir.recognition.controller;

import com.example.bsuir.recognition.dto.response.RecongitionResponse;
import com.example.bsuir.recognition.service.RecognitionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/recognition")
@AllArgsConstructor
public class RecognitionController {

    private final RecognitionService service;

    @PostMapping()
    public List<RecongitionResponse> getAll(@RequestBody Map<Integer, Double> descriptor) {
        return service.getAll(descriptor);
    }

    @PostMapping("/most-similar")
    public List<RecongitionResponse> getMostSimilar(@RequestBody Map<Integer, Double> descriptor) {
        return service.getMostSimilar(descriptor);
    }
}
