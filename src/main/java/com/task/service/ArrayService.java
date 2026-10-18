package com.task.service;

import com.task.entity.ArrayStatistics;
import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;

import java.util.Optional;

/**
 * Service interface for array operations.
 */
public interface ArrayService {

    Optional<Integer> findMin(IntegerArray array);

    Optional<Integer> findMax(IntegerArray array);

    Optional<Integer> calculateSum(IntegerArray array);

    Optional<Double> calculateAverage(IntegerArray array);
    Optional<ArrayStatistics> calculateStatistics(IntegerArray array);

    IntegerArray bubbleSort(IntegerArray array) throws ArrayUserException;

    IntegerArray quickSort(IntegerArray array) throws ArrayUserException;
}
