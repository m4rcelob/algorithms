package com.redeprivada;

import com.redeprivada.sorting.QuickSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int[] nums = Stream.iterate(1, n -> ThreadLocalRandom.current().nextInt(0, 1000))
                .limit(10)
                .mapToInt(Integer::intValue)
                .toArray();
        if (logger.isInfoEnabled())
            logger.info(Arrays.toString(nums));
        new QuickSort().sort(nums);
        if (logger.isInfoEnabled())
            logger.info(Arrays.toString(nums));
    }
}