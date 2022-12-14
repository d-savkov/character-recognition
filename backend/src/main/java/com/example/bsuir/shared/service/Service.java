package com.example.bsuir.shared.service;

import java.util.List;

public interface Service<T, ID> {

    T getById(ID id);

    List<T> getAll();
}
