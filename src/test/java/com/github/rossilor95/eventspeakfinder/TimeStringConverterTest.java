package com.github.rossilor95.eventspeakfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeStringConverterTest {
    private final TimeStringConverter timeStringConverter = new TimeStringConverter();

    @Test
    void shouldProperlyConvertTimeStringToMidnightSeconds() {
        // GIVEN an instance of TimeStringConverter

        // WHEN
        int result1 = timeStringConverter.toMidnightSeconds("00:00:00");
        int result2 = timeStringConverter.toMidnightSeconds("01:01:00");
        int result3 = timeStringConverter.toMidnightSeconds("23:59:59");

        // THEN
        assertEquals(0, result1);
        assertEquals(3660, result2);
        assertEquals(86399, result3);
    }

    @Test
    void shouldProperlyConvertMidnightSecondsToTimeString() {
        // GIVEN an instance of TimeStringConverter

        // WHEN
        String result1 = timeStringConverter.fromMidnightSeconds(0);
        String result2 = timeStringConverter.fromMidnightSeconds(3660);
        String result3 = timeStringConverter.fromMidnightSeconds(86399);

        // THEN
        assertEquals("00:00:00", result1);
        assertEquals("01:01:00", result2);
        assertEquals("23:59:59", result3);
    }
}
