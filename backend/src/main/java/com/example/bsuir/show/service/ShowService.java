package com.example.bsuir.show.service;

import com.example.bsuir.shared.service.Service;
import com.example.bsuir.show.dto.request.CreateShowRequest;
import com.example.bsuir.show.dto.request.UpdateShowRequest;
import com.example.bsuir.show.model.Show;

public interface ShowService extends Service<Show, Long> {

    Show create(CreateShowRequest request);

    void deleteById(Long id);

    Show updateById(Long id, UpdateShowRequest request);
}
