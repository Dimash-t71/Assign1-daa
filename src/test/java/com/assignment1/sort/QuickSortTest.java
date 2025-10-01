package com.assignment1.sort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.assignment1.metrics.Metrics;

class QuickSortTest {

    @Test
    void testQuickSortSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr, "QuickSort sorted array should match Arrays.sort()");
    }

    @Test
    void testQuickSortRandomArray() {
        int[] arr = ThreadLocalRandom.current().ints(1000, 0, 10000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr, "QuickSort sorted array should match Arrays.sort()");
    }
}