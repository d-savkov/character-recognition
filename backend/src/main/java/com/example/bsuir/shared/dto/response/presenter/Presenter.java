package com.example.bsuir.shared.dto.response.presenter;

import com.example.bsuir.shared.dto.response.Response;

public interface Presenter<E, D extends Response<E>> {

    D toDto(E entity);
}
