package com.github.rossilor95.peakintervalfinder;

/**
 * Enum representing the type of interval endpoint.
 */
enum EndpointType {

    /**
     * Represents the start of a time interval.
     */
    START('S'),

    /**
     * Represents the end of a time interval.
     */
    END('E');

    private final char flag;

    EndpointType(char flag) {
        this.flag = flag;
    }

    /**
     * Get the character flag associated with the endpoint type.
     *
     * @return The character flag
     */
    public char getFlag() {
        return flag;
    }
}