package com.assignment1.sort;

import com.assignment1.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length <= 1) return;
        int[] buf = new int[a.length];
        sort(a, buf, 0, a.length, m);
    }

    private static void sort(int[] a, int[] buf, int l, int r, Metrics m) {
        m.enter();
        try {
            int n = r - l;
            if (n <= 1) return;
            if (n <= CUTOFF) {
                insertionSort(a, l, r, m);
                return;
            }
            int mid = (l + r) >>> 1;
            sort(a, buf, l, mid, m);
            sort(a, buf, mid, r, m);
            int i = l, j = mid, k = l;
            while (i < mid || j < r) {
                if (i < mid && (j >= r || a[i] <= a[j])) {
                    buf[k++] = a[i++];
                } else {
                    buf[k++] = a[j++];
                }
            }
            System.arraycopy(buf, l, a, l, n);
            m.allocations += n;
        } finally {
            m.exit();
        }
    }

    private static void insertionSort(int[] a, int l, int r, Metrics m) {
        for (int i = l + 1; i < r; i++) {
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
}