package com.task.reader;

import com.task.exception.ArrayUserException;

import java.util.List;

/**
 * Service interface for reading lines from a file.
 */
public interface DataReader {

    List<String> readLines(String filePath) throws ArrayUserException;
}
