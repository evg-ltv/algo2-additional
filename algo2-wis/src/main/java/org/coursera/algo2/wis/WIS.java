package org.coursera.algo2.wis;

import java.util.ArrayList;
import java.util.List;

/**
 * Weighted Independent Set Problem
 * Algorithm using Dynamic Programming
 */
public class WIS {

    private final int[] path;
    private int[] memo;

    /**
     * Creates an instance to solve WIS Problem
     * @param path path graph with weights
     */
    public WIS(int[] path) {
        this.path = path;
    }

    /**
     * Runs Dynamic Programming Algorithm
     * and returns a maximum weight
     */
    public long calculateMaximumWeight() {
        memo = new int[path.length];
        memo[0] = path[0];
        if (memo.length == 1)
            return memo[0];
        memo[1] = Math.max(path[0], path[1]);
        for (int idx = 2; idx < path.length; idx++) {
            memo[idx] = Math.max(memo[idx - 1], memo[idx - 2] + path[idx]);
        }
        return memo[memo.length - 1];
    }

    /**
     * Reconstructs an Independent Set once
     * Dynamic Programming Algorithm has been run
     */
    public List<Integer> reconstructIndependentSet() {
        if (memo == null) {
            throw new IllegalStateException("Dynamic Programming Algorithm must be run before Reconstruction");
        }
        List<Integer> independentSet = new ArrayList<Integer>();
        int idx = memo.length - 1;
        while (idx >= 0) {
            if (idx == 0 || memo[idx] != memo[idx - 1]) {
                // current element is part of independent set
                independentSet.add(path[idx]);
                idx -= 2;
            }
            else {
                // current element is NOT part of independent set
                idx--;
            }
        }
        return independentSet;
    }

}
