package org.coursera.algo2.bellmanford;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple Graph Builder
 * Builds a directed graph which is Map<FromNode, Map<ToNode, Weight>>
 * Nodes and edges can be added separately: if an edge is added
 * and one of its nodes isn't there yet, it will be added.
 * If an edge is already there, its weight will be overwritten.
 * @param <V> Node's type
 * @param <W> Weight's type
 */
public class GraphBuilder<V, W> {

    private Map<V, Map<V, W>> graph;

    public GraphBuilder() {
        graph = new HashMap<V, Map<V, W>>();
    }

    public void addNode(V node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new HashMap<V, W>());
        }
    }

    public void addEdge(V fromNode, V toNode, W weight) {
        addNode(fromNode);
        addNode(toNode);
        graph.get(fromNode).put(toNode, weight);
    }

    public Map<V, Map<V, W>> getGraph() {
        return graph;
    }

}
