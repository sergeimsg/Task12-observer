package edu.melnik.task12.parser;

import edu.melnik.task12.exception.ArrayUserException;

/**
 * Service interface for parsing a string into a primitive int array.
 */
public interface ArrayParser {

    int[] parse(String lineOfNumbers) throws ArrayUserException;
}
