package com.assignment1.select;

import com.assignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 1, 9, 3, 7};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            Metrics m = new Metrics();
            int val = Select.deterministicSelect(arr.clone(), k, m);
            assertEquals(sorted[k], val, "k=" + k + " үшін нәтиже дұрыс емес");
        }
    }

    @Test
    void testRandomArray() {
        int n = 500;
        int[] arr = ThreadLocalRandom.current().ints(n, 0, n * 10).toArray();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < n; k += 50) { // әр 50-ші индексті тексереміз
            Metrics m = new Metrics();
            int val = Select.deterministicSelect(arr.clone(), k, m);
            assertEquals(sorted[k], val, "k=" + k + " үшін нәтиже дұрыс емес");
        }
    }
}