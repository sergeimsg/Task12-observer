package com.task.parser;

import com.task.exception.ArrayUserException;

/**
 * Service interface for parsing a string into a primitive int array.
 */
public interface ArrayParser {

    int[] parse(String lineOfNumbers) throws ArrayUserException;
}
