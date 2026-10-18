package com.task.reader;

import com.task.exception.ArrayUserException;
import com.task.reader.impl.DataReaderImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DataReaderImplTest {
    public static final Logger logger = LogManager.getLogger();
    private DataReaderImpl reader;

    @BeforeEach
    void setUp() {
        reader = new DataReaderImpl();
    }

    @Test
    void readLinesReturnsContent() throws ArrayUserException {
        String path = "src/test/resources/data/test_data.txt";
        List<String> lines = reader.readLines(path);
        assertNotNull(lines);
        assertTrue(lines.size() > 0);
    }

    @Test
    void readNonExistentFileThrowsException() {
        assertThrows(ArrayUserException.class, () -> reader.readLines("nonexistent_file.txt"));
    }

    @Test
    void readNullPathThrowsException() {
        assertThrows(ArrayUserException.class, () -> reader.readLines(null));
    }

    @Test
    void readEmptyPathThrowsException() {
        assertThrows(ArrayUserException.class, () -> reader.readLines(""));
        logger.info("{} passed", DataReaderImplTest.this);
    }


}
