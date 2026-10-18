package com.task.parser;

import com.task.exception.ArrayUserException;
import com.task.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserImplTest {

    private ArrayParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new ArrayParserImpl();
    }

    @Test
    void parseLineWithCommas() throws ArrayUserException {
        int[] result = parser.parse("1, 2, 3");
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }

    @Test
    void parseLineWithSemicolons() throws ArrayUserException {
        int[] result = parser.parse("1; 2; 3");
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }

    @Test
    void parseLineWithSpaces() throws ArrayUserException {
        int[] result = parser.parse("3 4 7");
        assertArrayEquals(new int[]{3, 4, 7}, result);
    }

    @Test
    void parseLineWithHyphens() throws ArrayUserException {
        int[] result = parser.parse("11- 2 - 42-");
        assertArrayEquals(new int[]{11, 2, 42}, result);
    }

    @Test
    void parseEmptyLine() throws ArrayUserException {
        int[] result = parser.parse("");
        assertEquals(0, result.length);
    }

    @Test
    void parseWhitespaceLine() throws ArrayUserException {
        int[] result = parser.parse("   ");
        assertEquals(0, result.length);
    }

    @Test
    void parseSingleNumber() throws ArrayUserException {
        int[] result = parser.parse("42");
        assertArrayEquals(new int[]{42}, result);
    }

    @Test
    void parseNullThrowsException() {
        assertThrows(ArrayUserException.class, () -> parser.parse(null));
    }
}
