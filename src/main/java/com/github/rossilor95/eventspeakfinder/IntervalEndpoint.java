package com.github.rossilor95.eventspeakfinder;

import java.time.LocalTime;

/**
 * Represents an endpoint of a time interval.
 */
public record IntervalEndpoint(LocalTime timeStamp, EndpointType type) {
}
