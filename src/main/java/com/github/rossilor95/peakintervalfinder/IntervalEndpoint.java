package com.github.rossilor95.peakintervalfinder;

import java.time.LocalTime;

/**
 * Represents one endpoint of a time interval (start or end).
 */
public record IntervalEndpoint(LocalTime time, EndpointType type) {
}
