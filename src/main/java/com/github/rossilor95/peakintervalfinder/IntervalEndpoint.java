package com.github.rossilor95.peakintervalfinder;

import java.time.LocalTime;

/**
 * Represents one endpoint of a time interval (start or end).
 */
record IntervalEndpoint(LocalTime time, Type type) {

    enum Type {
        START, END
    }
}
