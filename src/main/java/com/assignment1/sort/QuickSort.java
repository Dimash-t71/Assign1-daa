package com.assignment1.sort;

import com.assignment1.metrics.Metrics;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length <= 1) return;
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int l, int r, Metrics m) {
        if (r - l + 1 <= CUTOFF) {
            insertionSort(a, l, r, m);
            return;
        }
        if (l < r) {
            m.enter();
            try {
                int p = partition(a, l, r, m);
                quicksort(a, l, p - 1, m);
                quicksort(a, p + 1, r, m);
            } finally {
                m.exit();
            }
        }
    }

    private static int partition(int[] a, int l, int r, Metrics m) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(l, r + 1);
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, r, m);

        int i = l - 1;
        for (int j = l; j < r; j++) {
            m.compares++;
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j, m);
            }
        }
        swap(a, i + 1, r, m);
        return i + 1;
    }

    private static void insertionSort(int[] a, int l, int r, Metrics m) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= l && a[j] > key) {
                a[j + 1] = a[j];
                j--;
                m.compares++;
                m.swaps++;
            }
            a[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j, Metrics m) {
        if (i != j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            m.swaps++;
        }
    }
}
