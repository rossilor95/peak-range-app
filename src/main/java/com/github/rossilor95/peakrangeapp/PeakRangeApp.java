package com.github.rossilor95.peakrangeapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.github.rossilor95.peakrangeapp.IntervalEndpoint.Type.START;

public class PeakRangeApp {
    private static final Logger LOG = Logger.getLogger(PeakRangeApp.class.getName());
    private final TimeIntervalDataProcessor timeIntervalDataProcessor;

    PeakRangeApp(TimeIntervalDataProcessor timeIntervalDataProcessor) {
        this.timeIntervalDataProcessor = timeIntervalDataProcessor;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.exit(-1);
        }
        String filePath = args[0];

        final TimeIntervalDataProcessor timeIntervalDataProcessor = new TimeIntervalDataProcessor();
        final PeakRangeApp peakRangeApp = new PeakRangeApp(timeIntervalDataProcessor);
        List<TimeInterval> peakIntervals = peakRangeApp.findPeakRanges(filePath);

        LOG.log(Level.INFO, "Peak Interval(s) found: " + peakIntervals.toString());
    }

    public List<TimeInterval> findPeakRanges(String filePath) throws IOException {
        List<IntervalEndpoint> endpoints = timeIntervalDataProcessor.processDataFile(filePath);
        int[] eventCount = findEventCount(endpoints);
        int maxEventCount = Arrays.stream(eventCount)
                .max()
                .orElseThrow(() -> new RuntimeException("No max event count found"));
        LOG.log(Level.INFO, "Max event count: " + maxEventCount);
        List<Integer> maxIndices = findMaxIndices(eventCount, maxEventCount);
        return findPeakRanges(endpoints, maxIndices);
    }

    private int[] findEventCount(List<IntervalEndpoint> endpoints) {
        int[] eventCount = new int[endpoints.size()];
        eventCount[0] = endpoints.getFirst().type() == START ? 1 : 0;
        for (int i = 1; i < endpoints.size(); i++) {
            int previousEventCount = eventCount[i - 1];
            eventCount[i] = endpoints.get(i).type() == START
                    ? previousEventCount + 1
                    : previousEventCount - 1;
        }
        return eventCount;
    }

    private List<Integer> findMaxIndices(int[] arr, int max) {
        List<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                maxIndices.add(i);
            }
        }
        return maxIndices;
    }

    private List<TimeInterval> findPeakRanges(List<IntervalEndpoint> endpoints, List<Integer> maxIndices) {
        List<TimeInterval> peakIntervals = new ArrayList<>();
        for (int index : maxIndices) {
            TimeInterval peakRange = new TimeInterval(endpoints.get(index).time(), endpoints.get(index + 1).time());
            peakIntervals.add(peakRange);
        }
        return peakIntervals;
    }
}