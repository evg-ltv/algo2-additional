package org.coursera.algo2.bellmanford;

import java.util.HashMap;
import java.util.Map;

/**
 * Bellman-Ford Algorithm implementation
 */
public class BellmanFord {

    private Map<Integer, Map<Integer, Integer>> graph;
    private Map<Integer, Integer> shortestPathsWeights;

    public BellmanFord(Map<Integer, Map<Integer, Integer>> graph) {
        this.graph = graph;
    }

    /**
     * Runs Bellman-Ford Algorithm.
     * Assumes that nodes have names from 1 to N.
     * @param source starting from this node
     * @throws NegativeCycleException if a negative cycle is detected
     *
     * Possible enhancements: no need to have 2D array because we only need
     * to reach sub-problems of size N-1.
     * This will reduce total space from O(N^2) to O(N)
     */
    public void run(int source) throws NegativeCycleException {
        int nodesCount = graph.size();
        int[][] memo = new int[nodesCount + 1][nodesCount + 1];
        // initialization: for sub-problem size 0 path to source node is 0,
        // paths to other nodes are infinity
        for (int nodeId = 1; nodeId <= nodesCount; nodeId++) {
            if (nodeId == source) {
                memo[0][nodeId] = 0;
            }
            else {
                memo[0][nodeId] = Integer.MAX_VALUE;
            }
        }
        // reachableNodes contains nodes we can reach after i hops
        Map<Integer, Integer> reachableNodes = graph.get(source);
        for (int subProblemSize = 1; subProblemSize <= nodesCount; subProblemSize++) {
            Map<Integer, Integer> nextReachableNodes = new HashMap<Integer, Integer>();
            for (int nodeId = 1; nodeId <= nodesCount; nodeId++) {
                if (!reachableNodes.containsKey(nodeId)) {
                    memo[subProblemSize][nodeId] = memo[subProblemSize - 1][nodeId];
                }
                else {
                    memo[subProblemSize][nodeId] = Math.min(memo[subProblemSize - 1][nodeId],
                                                            reachableNodes.get(nodeId));
                    for (Map.Entry<Integer, Integer> entry : graph.get(nodeId).entrySet()) {
                        int nextNodeId = entry.getKey();
                        int edgeWeight;
                        if (memo[subProblemSize][nodeId] != Integer.MAX_VALUE) {
                            edgeWeight = memo[subProblemSize][nodeId] + entry.getValue();
                        }
                        else {
                            edgeWeight = entry.getValue();
                        }
                        if (!nextReachableNodes.containsKey(nextNodeId)) {
                            nextReachableNodes.put(nextNodeId, edgeWeight);
                        }
                        else {
                            nextReachableNodes.put(nextNodeId, Math.min(edgeWeight,
                                                                        nextReachableNodes.get(nextNodeId)));
                        }
                    }
                }
            }
            reachableNodes = nextReachableNodes;
        }
        // fill the map with results while checking if there's a negative cycle
        shortestPathsWeights = new HashMap<Integer, Integer>();
        for (int nodeId = 1; nodeId <= nodesCount; nodeId++) {
            if (memo[nodesCount][nodeId] < memo[nodesCount - 1][nodeId]) {
                shortestPathsWeights = null;
                throw new NegativeCycleException();
            }
            shortestPathsWeights.put(nodeId, memo[nodesCount - 1][nodeId]);
        }
    }

    /**
     * Returns shortest path to destination node
     */
    public int shortestPathTo(int destination) {
        if (shortestPathsWeights == null) {
            throw new IllegalStateException("Shortest paths are not available");
        }
        return shortestPathsWeights.get(destination);
    }

}
