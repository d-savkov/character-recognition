package com.example.bsuir.image.controller;

import com.example.bsuir.image.dto.request.CreateImageRequest;
import com.example.bsuir.image.dto.response.ImagePresignResponse;
import com.example.bsuir.image.dto.response.ImageResponse;
import com.example.bsuir.image.dto.response.presenter.ImagePresenter;
import com.example.bsuir.image.service.ImagePresignService;
import com.example.bsuir.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/show/{showId}/character/{characterId}/image")
public class ImageController {

    private final ImagePresignService presignService;
    private final ImageService service;
    private final ImagePresenter presenter;

    @GetMapping()
    public ImagePresignResponse getPresignResponse(@PathVariable Long showId,
                                                   @PathVariable Long characterId,
                                                   @RequestParam String fileType) {
        return presignService.getPresignResponse(showId, characterId, fileType);
    }

    @PostMapping()
    public ImageResponse create(@PathVariable Long showId,
                                @PathVariable Long characterId,
                                @RequestBody CreateImageRequest request) {
        return presenter.toDto(service.create(request));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long showId,
                           @PathVariable Long characterId,
                           @PathVariable Long id) {
        service.deleteById(id);
    }

}
