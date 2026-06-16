package com.redeprivada;

import com.redeprivada.sorting.Quicksort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int[] nums = Stream.iterate(1, n -> ThreadLocalRandom.current().nextInt(0, 1000))
                .limit(10)
                .mapToInt(Integer::intValue)
                .toArray();
        if (logger.isInfoEnabled())
            logger.info(Arrays.toString(nums));
        Quicksort.quicksort(nums);
        if (logger.isInfoEnabled())
            logger.info(Arrays.toString(nums));
    }
}