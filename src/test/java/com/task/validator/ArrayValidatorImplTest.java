package com.task.validator;

import com.task.validator.impl.ArrayValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayValidatorImplTest {

    private ArrayValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new ArrayValidatorImpl();
    }

    @Test
    void validLineWithCommas() {
        assertTrue(validator.isLineValid("1, 2, 3"));
    }

    @Test
    void validLineWithSemicolons() {
        assertTrue(validator.isLineValid("1; 2; 3"));
    }

    @Test
    void validLineWithSpaces() {
        assertTrue(validator.isLineValid("3 4 7"));
    }

    @Test
    void validLineWithHyphens() {
        assertTrue(validator.isLineValid("1 - 2 - 3"));
    }

    @Test
    void validLineWithEnDash() {
        assertTrue(validator.isLineValid("1 \u2013 2 \u2013 3"));
    }

    @Test
    void validEmptyLine() {
        assertTrue(validator.isLineValid(""));
    }

    @Test
    void validWhitespaceLine() {
        assertTrue(validator.isLineValid("   "));
    }

    @Test
    void validSingleNumber() {
        assertTrue(validator.isLineValid("42"));
    }

    @Test
    void validTrailingHyphen() {
        assertTrue(validator.isLineValid("11- 2 - 42-"));
    }

    @Test
    void invalidLineWithLetters() {
        assertFalse(validator.isLineValid("1y1 21 32"));
    }

    @Test
    void invalidLineWithDoubleDots() {
        assertFalse(validator.isLineValid("1..2..3"));
    }

    @Test
    void invalidLineWithMixedErrors() {
        assertFalse(validator.isLineValid("1, 2, x3, 6..5, 77"));
    }

    @Test
    void invalidNullLine() {
        assertFalse(validator.isLineValid(null));
    }
}
