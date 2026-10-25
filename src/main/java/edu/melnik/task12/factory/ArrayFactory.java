package edu.melnik.task12.factory;

import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.exception.ArrayUserException;

/**
 * Factory interface for creating IntegerArray objects (Factory Method pattern).
 */
public interface ArrayFactory {

    IntegerArray createArray(int[] data) throws ArrayUserException;
    IntegerArray createArray(int id, int[] data) throws ArrayUserException;
}
