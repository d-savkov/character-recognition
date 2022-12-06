package com.example.bsuir.shared.dto;

public interface Mapper<E, D extends Dto<E>> {

    E toEntity(D dto);
}
