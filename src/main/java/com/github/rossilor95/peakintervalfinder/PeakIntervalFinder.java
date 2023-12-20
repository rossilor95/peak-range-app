package com.github.rossilor95.peakintervalfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeakIntervalFinder {
    private static final Logger LOG = Logger.getLogger(PeakIntervalFinder.class.getName());
    private final TimeIntervalDataProcessor timeIntervalDataProcessor;

    PeakIntervalFinder(TimeIntervalDataProcessor timeIntervalDataProcessor) {
        this.timeIntervalDataProcessor = timeIntervalDataProcessor;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.exit(-1);
        }
        String filePath = args[0];

        final TimeIntervalDataProcessor timeIntervalDataProcessor = new TimeIntervalDataProcessor();
        final PeakIntervalFinder peakIntervalFinder = new PeakIntervalFinder(timeIntervalDataProcessor);
        List<TimeInterval> peakIntervals = peakIntervalFinder.findPeakIntervals(filePath);

        LOG.log(Level.INFO, "Peak Interval(s) found: " + peakIntervals.toString());
    }

    public List<TimeInterval> findPeakIntervals(String filePath) throws IOException {
        List<IntervalEndpoint> endpoints = timeIntervalDataProcessor.processDataFile(filePath);
        int[] eventCount = findEventCount(endpoints);
        int maxEventCount = findMax(eventCount);
        LOG.log(Level.INFO, "Max event count: " + maxEventCount);
        List<Integer> maxIndices = findMaxIndices(eventCount, maxEventCount);
        return findPeakIntervals(endpoints, maxIndices);
    }

    private int[] findEventCount(List<IntervalEndpoint> endpoints) {
        int[] eventCount = new int[endpoints.size()];
        eventCount[0] = endpoints.get(0).type() == EndpointType.START ? 1 : 0;
        for (int i = 1; i < endpoints.size(); i++) {
            eventCount[i] =
                    endpoints.get(i).type() == EndpointType.START ? eventCount[i - 1] + 1 : eventCount[i - 1] - 1;
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

    private List<TimeInterval> findPeakIntervals(List<IntervalEndpoint> endpoints, List<Integer> maxIndices) {
        List<TimeInterval> peakIntervals = new ArrayList<>();
        for (int index : maxIndices) {
            TimeInterval peakInterval = new TimeInterval(endpoints.get(index).time(), endpoints.get(index + 1).time());
            peakIntervals.add(peakInterval);
        }
        return peakIntervals;
    }

    private int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}