package com.github.rossilor95.eventspeakfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TimeIntervalDataProcessorTest {
    private static final String NOT_EXISTING_FILE = "src/test/resources/data/not_existing.txt";
    private static final String EMPTY_FILE = "src/test/resources/data/empty.txt";
    private static final String TEST_FILE = "src/test/resources/data/test.txt";
    private static final String NOT_EXISTING_FILE_MESSAGE = "File does not exist";
    private static final String EMPTY_FILE_MESSAGE = "Empty file provided.";

    private final TimeIntervalDataProcessor timeIntervalDataProcessor = new TimeIntervalDataProcessor();

    @Test
    void shouldThrowWhenTryingToProcessNotExistingFile() {
        // GIVEN an instance of TimeIntervalDataProcessor

        // WHEN
        FileNotFoundException exception =
            assertThrows(FileNotFoundException.class, () -> timeIntervalDataProcessor.processDataFile(NOT_EXISTING_FILE));

        // THEN
        assertTrue(exception.getMessage().contains(NOT_EXISTING_FILE_MESSAGE));
    }

    @Test
    void shouldThrowWhenTryingToProcessEmptyFile() {
        // GIVEN an instance of TimeIntervalDataProcessor

        // WHEN
        IOException exception =
            assertThrows(IOException.class, () -> timeIntervalDataProcessor.processDataFile(EMPTY_FILE));

        // THEN
        assertTrue(exception.getMessage().contains(EMPTY_FILE_MESSAGE));
    }

    @Test
    void shouldProperlyProcessTimeIntervalDataWhenFileIsValid() throws IOException {
        // GIVEN an instance of TimeIntervalDataProcessor and
        List<IntervalEndpoint> expected = List.of(
            new IntervalEndpoint(LocalTime.of(12, 20, 5), EndpointType.START),
            new IntervalEndpoint(LocalTime.of(12, 30, 0), EndpointType.START),
            new IntervalEndpoint(LocalTime.of(12, 33, 44), EndpointType.START),
            new IntervalEndpoint(LocalTime.of(12, 58, 23), EndpointType.END),
            new IntervalEndpoint(LocalTime.of(13, 15, 40), EndpointType.END),
            new IntervalEndpoint(LocalTime.of(15, 2, 11), EndpointType.END),
            new IntervalEndpoint(LocalTime.of(15, 20, 0), EndpointType.START),
            new IntervalEndpoint(LocalTime.of(16, 0, 0), EndpointType.START),
            new IntervalEndpoint(LocalTime.of(16, 5, 30), EndpointType.END),
            new IntervalEndpoint(LocalTime.of(16, 30, 45), EndpointType.END)
        );

        // WHEN
        List<IntervalEndpoint> actual = timeIntervalDataProcessor.processDataFile(TEST_FILE);

        // THEN
        assertEquals(expected, actual);
    }
}
