package com.example.bsuir.image.service;

import com.example.bsuir.image.model.Image;
import com.example.bsuir.person.model.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<Image> findAll();

    void saveImage(MultipartFile multipartFile, String path, Person person);
}
