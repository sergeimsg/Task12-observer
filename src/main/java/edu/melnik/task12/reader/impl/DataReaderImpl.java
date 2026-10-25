package edu.melnik.task12.reader.impl;

import edu.melnik.task12.entity.IntegerArray;
import edu.melnik.task12.exception.ArrayUserException;
import edu.melnik.task12.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Implementation of DataReader.
 */
public class DataReaderImpl implements DataReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<String> readLines(String filePath) throws ArrayUserException {
        if (filePath == null || filePath.strip().isBlank()) {
            throw new ArrayUserException("File path cannot be null or empty");
        }
        try {
            Path path = Path.of(filePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            logger.info("Read {} lines from {} lines {}", lines.size(), filePath, lines);
            return lines;
        } catch (IOException e) {
            throw new ArrayUserException("Failed to read file: " + filePath, e);
        }
    }
}
