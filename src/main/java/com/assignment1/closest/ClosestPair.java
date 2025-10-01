package com.assignment1.closest;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Екі нүктенің арақашықтығы
    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Негізгі алгоритм
    public static double closest(Point[] points) {
        // x бойынша сұрыптаймыз
        Point[] ptsSorted = points.clone();
        Arrays.sort(ptsSorted, Comparator.comparingDouble(p -> p.x));
        return closestRec(ptsSorted, 0, ptsSorted.length - 1);
    }

    private static double closestRec(Point[] pts, int left, int right) {
        if (right - left <= 3) {
            // brute force
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;

        double dLeft = closestRec(pts, left, mid);
        double dRight = closestRec(pts, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        // Merge y-сорт
        Point[] temp = new Point[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (pts[i].y < pts[j].y) temp[k++] = pts[i++];
            else temp[k++] = pts[j++];
        }
        while (i <= mid) temp[k++] = pts[i++];
        while (j <= right) temp[k++] = pts[j++];
        System.arraycopy(temp, 0, pts, left, temp.length);

        // Strip тексеру
        Point[] strip = new Point[pts.length];
        int stripSize = 0;
        for (int p = left; p <= right; p++) {
            if (Math.abs(pts[p].x - midX) < d) {
                strip[stripSize++] = pts[p];
            }
        }

        for (i = 0; i < stripSize; i++) {
            for (j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < d; j++) {
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }

        return d;
    }
}