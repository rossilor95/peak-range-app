# Find Peak Range

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
Note that the event duration _includes_ the end time. For example, the previous anomalous event will be active from `12:33:44` until `13:02:11`, and will be inactive starting from `13:02:12`.
All the data related to a single day of anomalous events measurements are stored (unordered) in a text file. In such a data file, each line represents a single anomalous event. Example:

```yaml
### DAY 1 ###
13:13:00,14:03:07 # Event 1
12:33:44,13:02:11 # Event 2
12:20:05,12:58:23 # Event 3
13:33:44,13:02:11 # Event 4
```

Note that in the actual data files there are no comments. An example of data file can be found [here](src/test/resources/data/test.txt).

The scientists want to write an application that can find the time range with the maximum occurrence of anomalous events during a given day. We refer to this time range as the *Peak Range*. 

This is when **find-peak-range** comes into play!



## Algorithm Explanation

find-peak-range employs a straightforward algorithm to calculate the Peak Range(s) of a given data file. The key concepts is the active/inactive state of an anomalous event in relationship with a time range endpoint type. 

Let us suppose we have a list of time ranges. Let us label each range endpoint as `S` (start) or `E` (end) and sort the range endpoints in ascending order. If we loop through the sorted endpoint list, we can state that:
- When we encounter an S endpoint, a new anomalous event activates. Therefore, the active events count increases by 1.
- After we encounter an E endpoint, an ongoing anomalous event deactivates. Therefore, the active events count decreases by 1.

In light of this, here's an overview of the algorithm:

1. _Read and process the data file_: The application reads the data file containing the start and end times of anomalous events and processes it using an instance of `TimeIntervalDataProcessor`.
2. _Calculate event counts_: It calculates the number of active events at each endpoint using an array called `eventCount`. This array keeps track of the count of events at each endpoint, incrementing when an event starts and decrementing when an event ends.
3. _Find the maximum event count_: The algorithm finds the maximum value in the `eventCount` array, which corresponds to the maximum number of concurrent anomalous events.
4. _Identify peak ranges_: It identifies the S endpoints where the event count equals the maximum count. These endpoints represent the start times of peak ranges.
5. _Construct peak range objects_: Finally, the algorithm constructs `TimeInterval` objects based on the identified endpoints and returns a list of peak ranges.

The result is a list of peak ranges, each representing a time range with the highest occurrence of anomalous events.


## How to use

### Local Installation
To clone and run this application, you'll need Git and Java 21 installed on your computer. We use Gradle Wrapper to build and run the application, so no need to have Gradle on your local machine. From your command line:

```shell
# Clone this repository
$ git clone https://github.com/rossilor95/peak-interval-finder.git

# Go into the repository
$ cd peak-interval-finder

# Make gradlew executable
$ chmod +x gradlew

# Install dependencies
$ ./gradlew build

# Run the app 
$ ./gradlew run --args="your_data_file.txt"
```

### Docker
First, make sure that the Docker engine is running. Then, from your command line:

```shell
# Build the image from the project's Dockerfile
$ docker build --tag pif .

# Run a new container from the PIF image 
$ docker run -it --mount type=bind,src=/absolute/path/to/your/data,target=/app/data.txt pif:latest /app/data.txt
```


