package com.example.bsuir.image.provider.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.image.provider.ImageBase64Provider;
import com.example.bsuir.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiskImageBase64Provider implements ImageBase64Provider {

    private final ImageRepository imageRepository;

    @Override
    public String get(Character character) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(getMainImage(character));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException(String.format(
                    "Can't get image byte array for person with id %s",
                    character.getId()
            ));
        }
    }

    private File getMainImage(Character character) {
        List<Image> images = imageRepository.findAllByCharacterId(character.getId());
        List<File> files = new ArrayList<>();
        for (Image image : images) {
            try {
                files.add(ResourceUtils.getFile("classpath:image/" + image.getPath()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return Objects.requireNonNull(files.get(0));
    }
}
