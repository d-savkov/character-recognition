package com.example.bsuir.shared.dto.request.mapper;

import com.example.bsuir.shared.dto.request.Request;

public interface UpdateMapper<E, D extends Request<E>, ID> {

    E toEntity(ID id, D dto);
}
