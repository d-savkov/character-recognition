package com.example.bsuir.shared.service;

import com.example.bsuir.shared.dto.request.Request;

import java.util.List;

public interface Service<T, ID> {

    T getById(ID id);

    List<T> getAll();
}
