package com.github.rossilor95.peakintervalfinder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for processing time interval data files.
 */
class TimeIntervalDataProcessor {
    private static final Logger LOG = Logger.getLogger(TimeIntervalDataProcessor.class.getName());

    /**
     * Processes time interval data from the specified file.
     *
     * @param filePath The path to the file containing time interval strings in the format "{@code start_time,end_time}"
     * @return A list of interval endpoints sorted in ascending order by time
     * @throws IOException           If an I/O error occurs while reading the provided file or if the provided file is empty
     * @throws FileNotFoundException If the file does not exist
     */
    public List<IntervalEndpoint> processDataFile(String filePath) throws IOException {
        List<IntervalEndpoint> endpoints = parseIntervals(filePath);
        return sortEndpoints(endpoints);
    }

    private List<IntervalEndpoint> parseIntervals(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            throw new FileNotFoundException("File does not exist.");
        }
        if (Files.size(path) == 0) {
            throw new IOException("Empty file provided.");
        }
        LOG.log(Level.INFO ,"Reading time intervals from: " + path.toAbsolutePath());
        List<String> intervals = Files.readAllLines(path);
        return extractEndpoints(intervals);
    }

    private List<IntervalEndpoint> extractEndpoints(List<String> intervals) {
        List<IntervalEndpoint> endpoints = new ArrayList<>();
        for (String interval : intervals) {
            String[] parts = interval.split(",");
            endpoints.add(new IntervalEndpoint(LocalTime.parse(parts[0]), EndpointType.START));
            endpoints.add(new IntervalEndpoint(LocalTime.parse(parts[1]), EndpointType.END));
        }
        return endpoints;
    }

    private List<IntervalEndpoint> sortEndpoints(List<IntervalEndpoint> endpoints) {
        return endpoints.stream().sorted(Comparator.comparing(IntervalEndpoint::time)).toList();
    }
}
