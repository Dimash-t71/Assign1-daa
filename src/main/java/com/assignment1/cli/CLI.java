package com.assignment1.cli;

import com.assignment1.metrics.Metrics;
import com.assignment1.sort.MergeSort;
import com.assignment1.sort.QuickSort;
import com.assignment1.select.Select;
import com.assignment1.closest.ClosestPair;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CLI {
    public static void main(String[] args) {
        int n = 1000;              // массив/нүктелер саны
        String algo = "merge";     // әдепкі алгоритм
        int k = -1;                // select үшін индекс

        // Аргументтерді оқу
        if (args.length > 0) n = Integer.parseInt(args[0]);
        if (args.length > 1) algo = args[1].toLowerCase();
        if (algo.equals("select") && args.length > 2) k = Integer.parseInt(args[2]);

        Metrics m = new Metrics();
        int[] data = ThreadLocalRandom.current().ints(n, 0, n * 10).toArray();

        long t0 = System.nanoTime();
        String result;

        if (algo.equals("quick")) {
            QuickSort.sort(data, m);
            result = "QuickSort";

        } else if (algo.equals("select")) {
            if (k < 0 || k >= n) {
                System.out.println("k мәні дұрыс емес. 0 мен " + (n - 1) + " аралығында болу керек.");
                return;
            }
            int kth = Select.deterministicSelect(data.clone(), k, m);
            result = "Select k=" + k + " -> " + kth;

        } else if (algo.equals("closest")) {
            // ✅ Closest Pair of Points
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            ThreadLocalRandom rnd = ThreadLocalRandom.current();
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPair.Point(
                        rnd.nextDouble(0, 10000),
                        rnd.nextDouble(0, 10000)
                );
            }
            double ans = ClosestPair.closest(pts);
            result = "ClosestPair distance=" + ans;

        } else {
            MergeSort.sort(data, m);
            result = "MergeSort";
        }
        long t1 = System.nanoTime();

        // Нәтижелерді шығару
        if (algo.equals("merge") || algo.equals("quick")) {
            int[] copy = data.clone();
            Arrays.sort(copy);
            boolean ok = Arrays.equals(copy, data);

            System.out.println(result + " n=" + n
                    + " time(ms)=" + ((t1 - t0) / 1e6)
                    + " sorted=" + ok
                    + " maxDepth=" + m.maxDepth);

        } else if (algo.equals("select")) {
            System.out.println(result
                    + " n=" + n
                    + " time(ms)=" + ((t1 - t0) / 1e6)
                    + " compares=" + m.compares
                    + " swaps=" + m.swaps);

        } else if (algo.equals("closest")) {
            System.out.println(result
                    + " n=" + n
                    + " time(ms)=" + ((t1 - t0) / 1e6));
        }
    }
}