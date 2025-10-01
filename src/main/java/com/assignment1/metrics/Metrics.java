package com.assignment1.metrics;

public class Metrics {
    public long compares = 0;
    public long swaps = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    private ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);

    public void enter() {
        depth.set(depth.get() + 1);
        if (depth.get() > maxDepth) {
            maxDepth = depth.get();
        }
    }

    public void exit() {
        depth.set(depth.get() - 1);
    }

    public void reset() {
        compares = swaps = allocations = 0;
        maxDepth = 0;
        depth.set(0);
    }
}