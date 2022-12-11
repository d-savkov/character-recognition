package com.example.bsuir.shared.dto.request.mapper;

import com.example.bsuir.shared.dto.request.Request;

public interface Mapper<E, D extends Request<E>> {

    E toEntity(D dto);
}
