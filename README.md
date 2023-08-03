# Events Peak Finder

## Introduction
This application solves a famous algorithmic problem in a naive but (in my opinion) fun and understandable way. Relevant Stack Overflow threads:

- https://stackoverflow.com/questions/36119375/maximum-occurrence-of-any-event-in-time-range (2nd answer)
- https://stackoverflow.com/questions/2244964/finding-number-of-overlaps-in-a-list-of-time-ranges

## Problem statement
Let us suppose we have the following problem:

A scientific station is tasked with studying anomalous *events*. Each *event* has a duration. During a day, the scientific station measure the start time and the end time of each anomalous event with the format `<start-time>,<end-time>`. Example:

```
12:33:44,13:02:11
```

All the data related to a single day of anomalous events measurements are stored (unordered) in a text file. In such a data file, each line represents a single anomalous event. Example:

```yaml
### DAY 1 ###
13:13:00,14:03:07 # Event 1
12:33:44,13:02:11 # Event 2
12:20:05,12:58:23 # Event 3
13:33:44,13:02:11 # Event 4
```

Note that in the actual data files there are no comments. An example of data file can be found [here](src/test/resources/test_intervals.txt).

The scientists want to write an application that can find the time interval with the maximum occurrence of anomalous events during a given day. We call this interval *Events Peak*. 

This is when Events Peak Finder comes into play!
