package com.example.bsuir.shared.dto;

public interface Presenter<E, D extends Dto<E>> {

    D toDto(E entity);
}
