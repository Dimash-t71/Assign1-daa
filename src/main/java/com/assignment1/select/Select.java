package com.assignment1.select;

import com.assignment1.metrics.Metrics;

public class Select {

    // k — ізделетін индекс (0-based). Мысалы: k=0 -> min, k=n-1 -> max
    public static int deterministicSelect(int[] arr, int k, Metrics m) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array бос болмау керек");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k дұрыс емес");
        }
        return select(arr, 0, arr.length - 1, k, m);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics m) {
        while (true) {
            if (left == right) return arr[left];

            int pivotIndex = pivot(arr, left, right, m);
            pivotIndex = partition(arr, left, right, pivotIndex, m);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    // ⚡️ Упрощённый pivot — просто середина диапазона
    private static int pivot(int[] arr, int left, int right, Metrics m) {
        return (left + right) / 2;
    }

    // Partition функциясы
    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics m) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, m);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            m.compares++;
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i, m);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex, m);
        return storeIndex;
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                m.compares++;
                m.swaps++;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j, Metrics m) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            m.swaps++;
        }
    }
}