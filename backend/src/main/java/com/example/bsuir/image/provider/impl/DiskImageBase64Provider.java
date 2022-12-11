package com.example.bsuir.image.provider.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.image.provider.ImageBase64Provider;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Service
public class DiskImageBase64Provider implements ImageBase64Provider {

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

    private File getMainImage(Character character) throws FileNotFoundException {
        File imageFolder = ResourceUtils.getFile("classpath:image/" + character.getImagesSource());
        return Objects.requireNonNull(imageFolder.listFiles())[0];
    }
}
