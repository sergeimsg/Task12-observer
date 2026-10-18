package com.task.factory;

import com.task.entity.IntegerArray;
import com.task.exception.ArrayUserException;

/**
 * Factory interface for creating IntegerArray objects (Factory Method pattern).
 */
public interface ArrayFactory {

    IntegerArray createArray(int[] data) throws ArrayUserException;
    IntegerArray createArray(int id, int[] data) throws ArrayUserException;
}
