package com.example.bsuir.show.controller;

import com.example.bsuir.show.dto.request.CreateShowRequest;
import com.example.bsuir.show.dto.response.ShowResponse;
import com.example.bsuir.show.dto.response.presenter.ShowResponsePresenter;
import com.example.bsuir.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/show")
public class ShowController {

    private final ShowService service;
    private final ShowResponsePresenter presenter;

    @GetMapping()
    public List<ShowResponse> getAll() {
        return service.getAll().stream().map(presenter::toDto).toList();
    }

    @GetMapping("/{id}")
    public ShowResponse getById(@PathVariable Long id) {
        return presenter.toDto(service.getById(id));
    }

    @PostMapping()
    public ShowResponse create(@RequestBody CreateShowRequest request) {
        return presenter.toDto(service.create(request));
    }
}
