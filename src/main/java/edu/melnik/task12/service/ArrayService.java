package edu.melnik.task12.service;

import edu.melnik.task12.entity.ArrayStatistics;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.exception.ArrayUserException;

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
