package com.example.bsuir.image.controller;

import com.example.bsuir.image.dto.response.ImagePresignResponse;
import com.example.bsuir.image.service.ImagePresignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/show/{showId}/character/{characterId}/image")
public class ImageController {

    private final ImagePresignService presignService;

    @GetMapping()
    public ImagePresignResponse getPresignResponse(@PathVariable Long showId,
                                                   @PathVariable Long characterId,
                                                   @RequestParam String fileType) {
        return presignService.getPresignResponse(showId, characterId, fileType);
    }
}
