package com.example.bsuir.shared.dto.request.mapper;

import com.example.bsuir.shared.dto.request.Request;

public interface CreateMapper<E, D extends Request<E>> {

    E toEntity(D dto);
}
