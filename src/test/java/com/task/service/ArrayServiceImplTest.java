package com.task.service;

import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;
import com.task.service.impl.ArrayServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayServiceImplTest {

    private ArrayServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ArrayServiceImpl();
    }

    @Test
    void findMinReturnsCorrectValue() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{5, 3, 8, 1, 9});
        Optional<Integer> result = service.findMin(array);
        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void findMinEmptyArrayReturnsEmpty() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        Optional<Integer> result = service.findMin(array);
        assertFalse(result.isPresent());
    }

    @Test
    void findMinThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.findMin(null));
    }

    @Test
    void findMaxReturnsCorrectValue() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{5, 3, 8, 1, 9});
        Optional<Integer> result = service.findMax(array);
        assertTrue(result.isPresent());
        assertEquals(9, result.get());
    }

    @Test
    void findMaxEmptyArrayReturnsEmpty() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        Optional<Integer> result = service.findMax(array);
        assertFalse(result.isPresent());
    }

    @Test
    void findMaxThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.findMax(null));
    }

    @Test
    void calculateSumReturnsCorrectValue() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{1, 2, 3, 4, 5});
        Optional<Integer> result = service.calculateSum(array);
        assertTrue(result.isPresent());
        assertEquals(15, result.get());
    }

    @Test
    void calculateSumEmptyArrayReturnsEmpty() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        Optional<Integer> result = service.calculateSum(array);
        assertFalse(result.isPresent());
    }

    @Test
    void calculateSumThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.calculateSum(null));
    }

    @Test
    void calculateAverageReturnsCorrectValue() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{2, 4, 6, 8});
        Optional<Double> result = service.calculateAverage(array);
        assertTrue(result.isPresent());
        assertEquals(5.0, result.get(), 0.001);
    }

    @Test
    void calculateAverageEmptyArrayReturnsEmpty() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        Optional<Double> result = service.calculateAverage(array);
        assertFalse(result.isPresent());
    }

    @Test
    void calculateAverageThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.calculateAverage(null));
    }

    @Test
    void bubbleSortSortsCorrectly() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{5, 3, 8, 1, 9, 2});
        IntegerArray sorted = service.bubbleSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, sorted.getData());
    }

    @Test
    void bubbleSortEmptyArray() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        IntegerArray sorted = service.bubbleSort(array);
        assertEquals(0, sorted.getLength());
    }

    @Test
    void bubbleSortThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.bubbleSort(null));
    }

    @Test
    void quickSortSortsCorrectly() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{10, 7, 8, 9, 1, 5});
        IntegerArray sorted = service.quickSort(array);
        assertArrayEquals(new int[]{1, 5, 7, 8, 9, 10}, sorted.getData());
    }

    @Test
    void quickSortEmptyArray() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{});
        IntegerArray sorted = service.quickSort(array);
        assertEquals(0, sorted.getLength());
    }

    @Test
    void quickSortThrowsOnNull() {
        assertThrows(ArrayUserException.class, () -> service.quickSort(null));
    }

    @Test
    void bubbleAndQuickSortProduceSameResult() throws ArrayUserException {
        IntegerArray array = new IntegerArray(new int[]{42, 17, 89, 3, 56, 21, 74, 38});
        IntegerArray bubbleSorted = service.bubbleSort(array);
        IntegerArray quickSorted = service.quickSort(array);
        assertArrayEquals(bubbleSorted.getData(), quickSorted.getData());
    }
}
