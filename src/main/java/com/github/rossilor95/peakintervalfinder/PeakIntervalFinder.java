package com.github.rossilor95.peakintervalfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeakIntervalFinder {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.exit(-1);
        }
        String filePath = args[0];

        final TimeIntervalDataProcessor timeIntervalDataProcessor = new TimeIntervalDataProcessor();
        List<IntervalEndpoint> endpoints = timeIntervalDataProcessor.processDataFile(filePath);

        int[] eventCount = findEventCount(endpoints);
        System.out.println(Arrays.toString(eventCount));

        int maxEventCount = findMax(eventCount);
        List<Integer> maxIndices = findMaxIndices(eventCount, maxEventCount);
        List<TimeInterval> peakIntervals = findPeakIntervals(endpoints, maxIndices);

        System.out.println(peakIntervals);
    }

    private static int[] findEventCount(List<IntervalEndpoint> endpoints) {
        int[] eventCount = new int[endpoints.size()];
        eventCount[0] = endpoints.get(0).type() == EndpointType.START ? 1 : 0;
        for (int i = 1; i < endpoints.size(); i++) {
            eventCount[i] =
                    endpoints.get(i).type() == EndpointType.START ? eventCount[i - 1] + 1 : eventCount[i - 1] - 1;
        }
        return eventCount;
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static List<Integer> findMaxIndices(int[] arr, int max) {
        List<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                maxIndices.add(i);
            }
        }
        return maxIndices;
    }

    private static List<TimeInterval> findPeakIntervals(List<IntervalEndpoint> endpoints, List<Integer> maxIndices) {
        List<TimeInterval> peakIntervals = new ArrayList<>();
        for (int index : maxIndices) {
            TimeInterval peakInterval = new TimeInterval(endpoints.get(index).time(), endpoints.get(index + 1).time());
            peakIntervals.add(peakInterval);
        }
        return peakIntervals;
    }
}