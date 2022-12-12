package com.example.bsuir.show.dto.request.mapper;

import com.example.bsuir.shared.dto.request.mapper.UpdateMapper;
import com.example.bsuir.show.dto.request.UpdateShowRequest;
import com.example.bsuir.show.model.Show;
import com.example.bsuir.show.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateShowRequestMapper implements UpdateMapper<Show, UpdateShowRequest, Long> {

    private final ShowRepository showRepository;

    @Override
    public Show toEntity(Long id, UpdateShowRequest dto) {
        Show show = showRepository.findByIdOrThrow(id);
        show.setName(dto.getName());
        show.setDescription(dto.getDescription());
        show.setShowType(dto.getShowType());
        return show;
    }
}
