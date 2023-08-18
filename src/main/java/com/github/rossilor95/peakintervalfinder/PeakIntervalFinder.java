package com.github.rossilor95.peakintervalfinder;

import java.io.IOException;
import java.util.List;

public class PeakIntervalFinder {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.exit(-1);
        }
        String filePath = args[0];

        final TimeIntervalDataProcessor timeIntervalDataProcessor = new TimeIntervalDataProcessor();
        List<IntervalEndpoint> endpoints = timeIntervalDataProcessor.processDataFile(filePath);
    }
}