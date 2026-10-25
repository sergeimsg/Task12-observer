package edu.melnik.task12.reader;

import edu.melnik.task12.exception.ArrayUserException;

import java.util.List;

/**
 * Service interface for reading lines from a file.
 */
public interface DataReader {

    List<String> readLines(String filePath) throws ArrayUserException;
}
