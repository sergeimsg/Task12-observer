package com.task.factory;

import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;
import com.task.factory.impl.IntegerArrayFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerArrayFactoryTest {

    private IntegerArrayFactory factory;

    @BeforeEach
    void setUp() {
        factory = new IntegerArrayFactory();
    }

    @Test
    void createArrayReturnsCorrectInstance() throws ArrayUserException {
        int[] data = {1, 2, 3, 4, 5};
        IntegerArray array = factory.createArray(data);
        assertNotNull(array);
        assertEquals(5, array.getLength());
        assertArrayEquals(data, array.getData());
    }

    @Test
    void createArrayWithEmptyData() throws ArrayUserException {
        int[] data = {};
        IntegerArray array = factory.createArray(data);
        assertNotNull(array);
        assertEquals(0, array.getLength());
    }

    @Test
    void createArrayWithNullThrowsException() {
        assertThrows(ArrayUserException.class, () -> factory.createArray(null));
    }

    @Test
    void createArrayDefensiveCopy() throws ArrayUserException {
        int[] data = {1, 2, 3};
        IntegerArray array = factory.createArray(data);
        data[0] = 999;
        assertNotEquals(999, array.getElementAt(0));
    }
}
