package com.example.bsuir.image.service.impl;

import com.example.bsuir.facedescriptor.provider.FaceDescriptorProvider;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.image.repository.ImageRepository;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FaceDescriptorProvider faceDescriptorProvider;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public void saveImage(MultipartFile multipartFile, String path, Person person) {
        Map<Integer, Double> faceDescriptorMap = faceDescriptorProvider.get(multipartFile);
        Image image = new Image(path, new ArrayList<>(faceDescriptorMap.values()), person);
        imageRepository.save(image);
    }
}
