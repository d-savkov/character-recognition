package com.example.bsuir.character.service.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.euclidean.provider.EuclideanDistanceProvider;
import com.example.bsuir.facedescriptor.provider.FaceDescriptorProvider;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.character.dto.response.CharacterSimilarityResponse;
import com.example.bsuir.character.service.CharacterRecognitionService;
import com.example.bsuir.character.service.CharacterService;
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
public class CharacterRecognitionServiceImpl implements CharacterRecognitionService {

    private final FaceDescriptorProvider faceDescriptorProvider;
    private final EuclideanDistanceProvider euclideanDistanceProvider;
    private final CharacterService characterService;
    private final ImageService imageService;
    private final Presenter<Character, CharacterSimilarityResponse> faceRecognitionPresenter;

    @Override
    public List<CharacterSimilarityResponse> findMostSimilar(MultipartFile file) {
        Map<Long, Double> euclideanImageMap = getEuclideanMap(file);
        Double euclideanMin = euclideanImageMap.values().stream()
                                               .min(Double::compareTo)
                                               .orElseThrow(() -> new IllegalArgumentException(
                                                       "Euclidean min is not present"));
        if (euclideanMin <= 0.6) {
            return euclideanImageMap.keySet().stream()
                                    .filter(key -> Objects.equals(euclideanImageMap.get(key), euclideanMin))
                                    .map(key -> {
                                        Character character = characterService.getByImageId(key);
                                        CharacterSimilarityResponse dto = faceRecognitionPresenter.toDto(character);
                                        dto.setSimilarity(euclideanToSimilarity(euclideanImageMap.get(key)));
                                        return dto;
                                    })
                                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Can't find similar person");
        }
    }

    @Override
    public List<CharacterSimilarityResponse> getAll(MultipartFile file) {
        List<CharacterSimilarityResponse> result = new ArrayList<>();
        getPersonSimilarityMap(getEuclideanMap(file)).forEach((person, similarity) -> {
            CharacterSimilarityResponse dto = faceRecognitionPresenter.toDto(person);
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

    private Map<Character, BigDecimal> getPersonSimilarityMap(Map<Long, Double> euclideanImageMap) {
        Map<Character, BigDecimal> result = new HashMap<>();
        euclideanImageMap.forEach((imageId, euclidean) -> {
            Character character = characterService.getByImageId(imageId);
            BigDecimal similarity = euclideanToSimilarity(euclidean);
            if (!result.containsKey(character) || similarity.compareTo(result.get(character)) > 0) {
                result.put(character, similarity);
            }
        });
        return result;
    }

    private BigDecimal euclideanToSimilarity(Double euclidean) {
        return BigDecimal.valueOf(Math.abs(1 - euclidean) * 100).setScale(1, RoundingMode.HALF_UP);
    }
}
