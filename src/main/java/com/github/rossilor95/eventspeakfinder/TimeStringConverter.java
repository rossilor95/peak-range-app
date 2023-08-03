package com.github.rossilor95.eventspeakfinder;

import java.util.Arrays;

/**
 * Utility class for converting between a 24-hour format time string (HH:mm:ss) and an amount of seconds from midnight.
 */
class TimeStringConverter {

    /**
     * Converts a 24-hour format time string into an amount of seconds from midnight.
     *
     * @param timeString The time string in HH:mm:ss format
     * @return The equivalent amount of seconds from midnight
     */
    int toMidnightSeconds(String timeString) {
        return Arrays.stream(timeString.split(":"))
            .mapToInt(Integer::parseInt)
            .reduce(0, (total, timePart) -> total * 60 + timePart);
    }

    /**
     * Converts an amount of seconds from midnight into a 24-hour format time string.
     *
     * @param midnightSeconds The amount of seconds from midnight
     * @return The equivalent time string in HH:mm:ss format
     */
    String fromMidnightSeconds(int midnightSeconds) {
        int hours = midnightSeconds / 3600;
        int minutes = (midnightSeconds % 3600) / 60;
        int remainingSeconds = midnightSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}
