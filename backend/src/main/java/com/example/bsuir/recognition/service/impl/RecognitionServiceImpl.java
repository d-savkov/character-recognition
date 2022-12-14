package com.example.bsuir.recognition.service.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.character.service.CharacterService;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.recognition.dto.response.RecongitionResponse;
import com.example.bsuir.recognition.dto.response.presenter.RecognitionPresenter;
import com.example.bsuir.recognition.service.RecognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecognitionServiceImpl implements RecognitionService {

    private final RecognitionPresenter presenter;
    private final CharacterService characterService;
    private final ImageService imageService;

    @Override
    public List<RecongitionResponse> getMostSimilar(Map<Integer, Double> descriptor) {
        Map<Long, Double> euclideanMap = getEuclideanMap(descriptor.values());
        Double euclidianMinimum = getEuclidianMinimum(euclideanMap);
        if (euclidianMinimum <= 0.6) {
            return euclideanMap.keySet().stream()
                               .filter(key -> Objects.equals(euclideanMap.get(key), euclidianMinimum))
                               .map(key -> {
                                   Character character = characterService.getByImageId(key);
                                   RecongitionResponse dto = presenter.toDto(character);
                                   dto.setSimilarity(euclideanToSimilarity(euclideanMap.get(key)));
                                   return dto;
                               })
                               .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Can't find similar person");
        }
    }

    @Override
    public List<RecongitionResponse> getAll(Map<Integer, Double> descriptor) {
        List<RecongitionResponse> result = new ArrayList<>();
        Map<Long, Double> euclideanMap = getEuclideanMap(descriptor.values());
        getSimilarityMap(euclideanMap).forEach((character, similarity) -> {
            RecongitionResponse dto = presenter.toDto(character);
            dto.setSimilarity(similarity);
            result.add(dto);
        });
        return result;
    }

    private Map<Long, Double> getEuclideanMap(Collection<Double> descriptorValues) {
        Map<Long, Double> result = new HashMap<>();
        imageService.getAll().forEach(image -> {
            Double euclidian = getEuclidianDistance(image.getFaceDescriptor(), descriptorValues.stream().toList());
            result.put(image.getId(), euclidian);
        });
        return result;
    }

    private Double getEuclidianMinimum(Map<Long, Double> map) {
        return map.values().stream()
                  .min(Double::compareTo)
                  .orElseThrow(() -> new IllegalArgumentException("Euclidean min is not present"));
    }

    private Map<Character, BigDecimal> getSimilarityMap(Map<Long, Double> euclideanMap) {
        Map<Character, BigDecimal> result = new HashMap<>();
        euclideanMap.forEach((imageId, euclidean) -> {
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

    private Double getEuclidianDistance(List<Double> l1, List<Double> l2) {
        if (l1.size() != l2.size()) {
            throw new IllegalArgumentException("Both list must have the same number of elements");
        }
        double sum = 0;
        for (int i = 0; i < l1.size(); i++) {
            double delta = (l2.get(i) - l1.get(i));
            sum += delta * delta;
        }
        return Math.sqrt(sum);
    }
}
