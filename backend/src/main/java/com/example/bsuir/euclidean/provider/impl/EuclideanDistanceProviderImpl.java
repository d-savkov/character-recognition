package com.example.bsuir.euclidean.provider.impl;

import com.example.bsuir.euclidean.provider.EuclideanDistanceProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuclideanDistanceProviderImpl implements EuclideanDistanceProvider {

    public double get(List<Double> l1, List<Double> l2) {
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
