package com.task.parser.impl;

import com.task.exception.ArrayUserException;
import com.task.parser.ArrayParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Parses a validated string line into a primitive int array.
 * Recognized delimiters: comma, semicolon, space, hyphen, en-dash.
 */
public class ArrayParserImpl implements ArrayParser {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public int[] parse(String lineOfNumbers) throws ArrayUserException {
        if (lineOfNumbers == null) {
            throw new ArrayUserException("Line cannot be null");
        }
        String trimmed = lineOfNumbers.trim();
        if (trimmed.isEmpty()) {
            return new int[0];
        }
        String normalized = trimmed
                .replace(',', ' ')
                .replace(';', ' ')
                .replace('-', ' ')
                .replace('\u2013', ' ')
                .trim();
        if (normalized.isEmpty()) {
            return new int[0];
        }
        String[] tokens = normalized.split("\\s+");
        logger.debug("normalized {}", Arrays.toString(tokens));
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            try {
                result[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                throw new ArrayUserException("Failed to parse token: " + tokens[i], e);
            }
        }
        logger.debug("Parsed {} elements from lineOfNumbers", result.length);
        return result;
    }
}
