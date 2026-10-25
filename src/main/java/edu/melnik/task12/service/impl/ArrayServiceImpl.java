package edu.melnik.task12.service.impl;

import edu.melnik.task12.entity.ArrayStatistics;
import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.exception.ArrayUserException;
import edu.melnik.task12.service.ArrayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * Implementation of ArrayService providing min/max search, sum, average,
 * and two sorting algorithms (bubble sort and quick sort).
 */
public class ArrayServiceImpl implements ArrayService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<Integer> findMin(IntegerArray array) {

        int[] data = array.getData();
        if (data.length == 0) {
            logger.info("Empty array: min is not present");
            return Optional.empty();
        }
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
    //    logger.info("Min value: {}", min);
        return Optional.of(min);
    }

    @Override
    public Optional<Integer> findMax(IntegerArray array) {
        int[] data = array.getData();
        if (data.length == 0) {
      //      logger.info("Empty array: max is not present");
            return Optional.empty();
        }

        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
    //    logger.info("Max value: {}", max);
        return Optional.of(max);
    }

    @Override
    public Optional<Integer> calculateSum(IntegerArray array) {

        int[] data = array.getData();
        if (array.getLength() == 0) {
            logger.info("Empty array: sum is not present");
            return Optional.empty();
        }
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
    //    logger.info("Sum: {}", sum);
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> calculateAverage(IntegerArray array) {

        int[] data = array.getData();
        if (data.length == 0) {
            logger.info("Empty array: average is not present");
            return Optional.empty();
        }
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        double average = (double) sum / data.length;
    //    logger.info("Average: {}", average);
        return Optional.of(average);
    }

    @Override
    public Optional<ArrayStatistics> calculateStatistics(IntegerArray array) {

        if (array.getLength() == 0) {
            logger.info("Empty array: no statistics at all");
            return Optional.empty();
        }
        Optional<Integer> min = findMin(array);
        Optional<Integer> max = findMax(array);
        Optional<Double> avr = calculateAverage(array);
        Optional<Integer> sum = calculateSum(array);
        int arrayLength = array.getLength();
        ArrayStatistics arrayStatistics = new ArrayStatistics(min.get(), max.get(), sum.get(), avr.get(), arrayLength);

        return Optional.of(arrayStatistics);
    }


    @Override
    public IntegerArray bubbleSort(IntegerArray array) throws ArrayUserException {
        if (array == null) {
            throw new ArrayUserException("Array cannot be null");
        }
        int[] data = array.getData();
        int id = array.getId();
        if (data.length == 0) {
            logger.info("Array is empty: nothing to sort with bubbleSort");
            return array;
        }
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    //    logger.info("Array sorted with bubble sort");
        return new IntegerArray(id, data);
    }

    @Override
    public IntegerArray quickSort(IntegerArray array) throws ArrayUserException {
        if (array == null) {
            throw new ArrayUserException("Array cannot be null");
        }
        int[] data = array.getData();
        int id = array.getId();
        if (data.length == 0) {
            logger.info("Array is empty: nothing to sort with quickSort");
            return array;
        }
        quickSortHelper(data, 0, data.length - 1);
    //    logger.info("Array sorted with quick sort");
        return new IntegerArray(id, data);
    }

    private void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
