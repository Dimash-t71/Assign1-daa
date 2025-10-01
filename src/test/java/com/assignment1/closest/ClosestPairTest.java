package com.assignment1.closest;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testSmallPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(10, 10)
        };

        double d = ClosestPair.closest(points);
        assertEquals(Math.sqrt(2), d, 1e-6);
    }

    @Test
    void testRandomPointsVsBruteForce() {
        int n = 500;
        ClosestPair.Point[] points = new ClosestPair.Point[n];
        Random rand = new Random(42);

        for (int i = 0; i < n; i++) {
            points[i] = new ClosestPair.Point(
                    rand.nextDouble() * 1000,
                    rand.nextDouble() * 1000
            );
        }

        double d1 = ClosestPair.closest(points.clone());
        double d2 = bruteForce(points);

        assertEquals(d2, d1, 1e-6);
    }

    private double bruteForce(ClosestPair.Point[] points) {
        double best = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                best = Math.min(best, Math.sqrt(dx * dx + dy * dy));
            }
        }
        return best;
    }
}