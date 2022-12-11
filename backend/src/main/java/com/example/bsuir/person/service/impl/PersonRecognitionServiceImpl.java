package com.example.bsuir.person.service.impl;

import com.example.bsuir.euclidean.provider.EuclideanDistanceProvider;
import com.example.bsuir.facedescriptor.provider.FaceDescriptorProvider;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.person.dto.response.PersonSimilarityResponse;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.person.service.PersonRecognitionService;
import com.example.bsuir.person.service.PersonService;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonRecognitionServiceImpl implements PersonRecognitionService {

    private final FaceDescriptorProvider faceDescriptorProvider;
    private final EuclideanDistanceProvider euclideanDistanceProvider;
    private final PersonService personService;
    private final ImageService imageService;
    private final Presenter<Person, PersonSimilarityResponse> faceRecognitionPresenter;

    @Override
    public List<PersonSimilarityResponse> findMostSimilar(MultipartFile file) {
        Map<Long, Double> euclideanImageMap = getEuclideanMap(file);
        Double euclideanMin = euclideanImageMap.values().stream()
                                               .min(Double::compareTo)
                                               .orElseThrow(() -> new IllegalArgumentException(
                                                       "Euclidean min is not present"));
        if (euclideanMin <= 0.6) {
            return euclideanImageMap.keySet().stream()
                                    .filter(key -> Objects.equals(euclideanImageMap.get(key), euclideanMin))
                                    .map(key -> {
                                        Person person = personService.getByImageId(key);
                                        PersonSimilarityResponse dto = faceRecognitionPresenter.toDto(person);
                                        dto.setSimilarity(euclideanToSimilarity(euclideanImageMap.get(key)));
                                        return dto;
                                    })
                                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Can't find similar person");
        }
    }

    @Override
    public List<PersonSimilarityResponse> getAll(MultipartFile file) {
        List<PersonSimilarityResponse> result = new ArrayList<>();
        getPersonSimilarityMap(getEuclideanMap(file)).forEach((person, similarity) -> {
            PersonSimilarityResponse dto = faceRecognitionPresenter.toDto(person);
            dto.setSimilarity(similarity);
            result.add(dto);
        });
        return result;
    }

    private Map<Long, Double> getEuclideanMap(MultipartFile file) {
        List<Double> faceDescriptor = new ArrayList<>(faceDescriptorProvider.get(file).values());
        Map<Long, Double> result = new HashMap<>();
        imageService.getAll().forEach(image -> {
            result.put(
                    image.getId(),
                    euclideanDistanceProvider.get(image.getFaceDescriptor(), faceDescriptor)
            );
        });
        return result;
    }

    private Map<Person, BigDecimal> getPersonSimilarityMap(Map<Long, Double> euclideanImageMap) {
        Map<Person, BigDecimal> result = new HashMap<>();
        euclideanImageMap.forEach((imageId, euclidean) -> {
            Person person = personService.getByImageId(imageId);
            BigDecimal similarity = euclideanToSimilarity(euclidean);
            if (!result.containsKey(person) || similarity.compareTo(result.get(person)) > 0) {
                result.put(person, similarity);
            }
        });
        return result;
    }

    private BigDecimal euclideanToSimilarity(Double euclidean) {
        return BigDecimal.valueOf(Math.abs(1 - euclidean) * 100).setScale(1, RoundingMode.HALF_UP);
    }
}
