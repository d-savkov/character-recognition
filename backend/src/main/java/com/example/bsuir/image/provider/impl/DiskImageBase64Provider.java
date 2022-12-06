package com.example.bsuir.image.provider.impl;

import com.example.bsuir.image.provider.ImageBase64Provider;
import com.example.bsuir.person.model.Person;
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
    public String get(Person person) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(getMainImage(person));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException(String.format(
                    "Can't get image byte array for person with id %s",
                    person.getId()
            ));
        }
    }

    private File getMainImage(Person person) throws FileNotFoundException {
        File imageFolder = ResourceUtils.getFile("classpath:image/" + person.getImagesSource());
        return Objects.requireNonNull(imageFolder.listFiles())[0];
    }
}
