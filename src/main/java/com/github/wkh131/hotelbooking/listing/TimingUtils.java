package com.github.wkh131.hotelbooking.listing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Utility functions for timing related activities.
 */
public class TimingUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimingUtils.class);

    /**
     * Executes and times the input task, logging (at INFO) the time using the given format.
     * @param task the task to execute.
     * @param format the format of the timing message, using the Logging format, and expecting to
     *              contain a single placeholder {} for the timing result in milliseconds.
     *              ex: "Took {} ms to get all results."
     */
    public static <T> void timeAndLog(Supplier<T> task, String format) {
        long start = System.nanoTime();
        task.get();
        long elapsed = System.nanoTime() - start;
        LOGGER.info(format, Duration.ofNanos(elapsed).toMillis());
    }
}
