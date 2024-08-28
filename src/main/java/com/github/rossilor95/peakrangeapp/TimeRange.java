package com.github.rossilor95.peakrangeapp;

import java.time.LocalTime;

public record TimeRange(LocalTime startTime, LocalTime endTime) {
    public TimeRange {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }
}
