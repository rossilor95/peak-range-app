package com.github.rossilor95.peakrangeapp;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.rossilor95.peakrangeapp.IntervalEndpoint.Type.END;
import static com.github.rossilor95.peakrangeapp.IntervalEndpoint.Type.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PeakRangeAppTest {
    private static final String FILE_PATH = "test_filepath";

    @Mock private TimeIntervalDataProcessor timeIntervalDataProcessor;

    @InjectMocks private PeakRangeApp underTest;

    @Test
    void shouldFindSinglePeakRangeWhenPresentInData() throws IOException {
        // GIVEN
        List<TimeRange> expected = List.of(new TimeRange(LocalTime.of(5, 20, 40), LocalTime.of(5, 48, 32)));
        List<IntervalEndpoint> endpoints = List.of(new IntervalEndpoint(LocalTime.of(5, 7, 16), START),
                                                   new IntervalEndpoint(LocalTime.of(5, 13, 29), START),
                                                   new IntervalEndpoint(LocalTime.of(5, 20, 40), START),
                                                   new IntervalEndpoint(LocalTime.of(5, 48, 32), END),
                                                   new IntervalEndpoint(LocalTime.of(5, 55, 7), END),
                                                   new IntervalEndpoint(LocalTime.of(6, 0, 56), END));
        given(timeIntervalDataProcessor.processDataFile(FILE_PATH)).willReturn(endpoints);

        // WHEN
        List<TimeRange> actual = underTest.findPeakRanges(FILE_PATH);

        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindMultiplePeakRangesWhenPresentInData() throws IOException {
        // GIVEN
        List<TimeRange> expected = List.of(new TimeRange(LocalTime.of(13, 13, 0), LocalTime.of(13, 23, 55)),
                                              new TimeRange(LocalTime.of(13, 58, 10), LocalTime.of(14, 2, 1)));
        List<IntervalEndpoint> endpoints = List.of(new IntervalEndpoint(LocalTime.of(13, 13, 0), START),
                                                   new IntervalEndpoint(LocalTime.of(13, 23, 55), END),
                                                   new IntervalEndpoint(LocalTime.of(13, 58, 10), START),
                                                   new IntervalEndpoint(LocalTime.of(14, 2, 1), END));
        given(timeIntervalDataProcessor.processDataFile(FILE_PATH)).willReturn(endpoints);

        // WHEN
        List<TimeRange> actual = underTest.findPeakRanges(FILE_PATH);

        // THEN
        assertEquals(expected, actual);
    }
}
