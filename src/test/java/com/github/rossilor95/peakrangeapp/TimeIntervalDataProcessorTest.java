package com.github.rossilor95.peakrangeapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import static com.github.rossilor95.peakrangeapp.IntervalEndpoint.Type.END;
import static com.github.rossilor95.peakrangeapp.IntervalEndpoint.Type.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeIntervalDataProcessorTest {
    private static final String NOT_EXISTING_FILE = "src/test/resources/data/not_existing.txt";
    private static final String EMPTY_FILE = "src/test/resources/data/empty.txt";
    private static final String TEST_FILE = "src/test/resources/data/test.txt";
    private static final String NOT_EXISTING_FILE_MESSAGE = "File does not exist";
    private static final String EMPTY_FILE_MESSAGE = "Empty file provided.";

    private final TimeIntervalDataProcessor underTest = new TimeIntervalDataProcessor();

    @Test
    void shouldThrowWhenTryingToProcessNotExistingFile() {
        // GIVEN an instance of TimeIntervalDataProcessor

        // WHEN
        FileNotFoundException exception = assertThrows(FileNotFoundException.class,
                                                       () -> underTest.processDataFile(NOT_EXISTING_FILE));

        // THEN
        assertTrue(exception.getMessage().contains(NOT_EXISTING_FILE_MESSAGE));
    }

    @Test
    void shouldThrowWhenTryingToProcessEmptyFile() {
        // GIVEN an instance of TimeIntervalDataProcessor

        // WHEN
        IOException exception = assertThrows(IOException.class, () -> underTest.processDataFile(EMPTY_FILE));

        // THEN
        assertTrue(exception.getMessage().contains(EMPTY_FILE_MESSAGE));
    }

    @Test
    void shouldProperlyProcessTimeIntervalDataWhenFileIsValid() throws IOException {
        // GIVEN an instance of TimeIntervalDataProcessor and
        List<IntervalEndpoint> expected = List.of(new IntervalEndpoint(LocalTime.of(12, 20, 5), START),
                                                  new IntervalEndpoint(LocalTime.of(12, 30, 0), START),
                                                  new IntervalEndpoint(LocalTime.of(12, 33, 44), START),
                                                  new IntervalEndpoint(LocalTime.of(12, 58, 23), END),
                                                  new IntervalEndpoint(LocalTime.of(13, 15, 40), END),
                                                  new IntervalEndpoint(LocalTime.of(15, 2, 11), END),
                                                  new IntervalEndpoint(LocalTime.of(15, 20, 0), START),
                                                  new IntervalEndpoint(LocalTime.of(16, 0, 0), START),
                                                  new IntervalEndpoint(LocalTime.of(16, 5, 30), END),
                                                  new IntervalEndpoint(LocalTime.of(16, 30, 45), END));

        // WHEN
        List<IntervalEndpoint> actual = underTest.processDataFile(TEST_FILE);

        // THEN
        assertEquals(expected, actual);
    }
}
