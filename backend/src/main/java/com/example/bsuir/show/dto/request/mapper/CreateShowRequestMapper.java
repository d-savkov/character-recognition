package com.example.bsuir.show.dto.request.mapper;

import com.example.bsuir.shared.dto.request.mapper.Mapper;
import com.example.bsuir.show.dto.request.CreateShowRequest;
import com.example.bsuir.show.model.Show;
import org.springframework.stereotype.Service;

@Service
public class CreateShowRequestMapper implements Mapper<Show, CreateShowRequest> {

    @Override
    public Show toEntity(CreateShowRequest dto) {
        return new Show(dto.getName(), dto.getDescription(), dto.getShowType());
    }
}