package com.assignment1.sort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.assignment1.metrics.Metrics;

class MergeSortTest {

    @Test
    void testMergeSortSmallArray() {
        int[] arr = {3, 1, 4, 1, 5, 9};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr, "MergeSort sorted array should match Arrays.sort()");
    }

    @Test
    void testMergeSortRandomArray() {
        int[] arr = ThreadLocalRandom.current().ints(1000, 0, 10000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr, "MergeSort sorted array should match Arrays.sort()");
    }
}