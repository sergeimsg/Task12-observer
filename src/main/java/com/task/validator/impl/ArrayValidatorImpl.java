package com.task.validator.impl;

import com.task.validator.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Validates whether a string line represents a valid integer array.
 * Valid delimiters: comma, semicolon, space, hyphen, en-dash.
 * Valid lines contain only digits and delimiters (or are empty/whitespace).
 */
public class ArrayValidatorImpl implements ArrayValidator {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean isLineValid(String lineOfNumbers) {
        if (lineOfNumbers == null) {
            return false;
        }
        String trimmed = lineOfNumbers.trim();
        if (trimmed.isEmpty()) {
            return true;
        }

       String normalized = trimmed
                .replace(',', ' ')
                .replace(';', ' ')
                .replace('-', ' ')
                .replace('\u2013', ' ')
                .trim();
        if (normalized.isEmpty()) {
            return true;
        }
        String[] tokens = normalized.split("\\s+");


       for (String token : tokens) {
            if (!token.matches("\\d+")) {
                logger.warn("Invalid token detected: '{}'", token);
                return false;
            }
        }
        return true;
    }
}
