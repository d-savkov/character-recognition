package com.example.bsuir.shared.validator;

import com.example.bsuir.shared.dto.Dto;

public interface Validator<E, D extends Dto<E>> {

    void validate(D dto);
}
