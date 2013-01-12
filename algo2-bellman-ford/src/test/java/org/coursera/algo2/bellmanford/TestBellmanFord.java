package org.coursera.algo2.bellmanford;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TestBellmanFord {

    /*
     * DAG with 5 nodes and 6 edges
     */
    private Map<Integer, Map<Integer, Integer>> buildTestInstance1() {
        GraphBuilder<Integer, Integer> gb = new GraphBuilder<Integer, Integer>();
        gb.addEdge(1, 2, 2);
        gb.addEdge(1, 3, 4);
        gb.addEdge(2, 3, 1);
        gb.addEdge(2, 4, 2);
        gb.addEdge(4, 5, 2);
        gb.addEdge(3, 5, 4);
        return gb.getGraph();
    }

    /*
     * Directed graph with a non-negative cycle (4 nodes and 4 edges)
     */
    private Map<Integer, Map<Integer, Integer>> buildTestInstance2() {
        GraphBuilder<Integer, Integer> gb = new GraphBuilder<Integer, Integer>();
        gb.addEdge(1, 2, 1);
        gb.addEdge(2, 3, 2);
        gb.addEdge(3, 4, -3);
        gb.addEdge(4, 1, 4);
        return gb.getGraph();
    }

    /*
     * Directed graph with a negative cycle (4 nodes and 4 edges)
     */
    private Map<Integer, Map<Integer, Integer>> buildTestInstance3() {
        GraphBuilder<Integer, Integer> gb = new GraphBuilder<Integer, Integer>();
        gb.addEdge(1, 2, 2);
        gb.addEdge(2, 3, -7);
        gb.addEdge(3, 4, 1);
        gb.addEdge(4, 1, 3);
        return gb.getGraph();
    }

    @Test
    public void testDAG() throws Exception {
        BellmanFord bf = new BellmanFord(buildTestInstance1());
        bf.run(1);
        assertEquals(0, bf.shortestPathTo(1));
        assertEquals(2, bf.shortestPathTo(2));
        assertEquals(3, bf.shortestPathTo(3));
        assertEquals(6, bf.shortestPathTo(5));
    }

    @Test
    public void testPositiveCycle() throws Exception {
        BellmanFord bf = new BellmanFord(buildTestInstance2());
        bf.run(1);
        assertEquals(0, bf.shortestPathTo(1));
        assertEquals(1, bf.shortestPathTo(2));
        assertEquals(3, bf.shortestPathTo(3));
        assertEquals(0, bf.shortestPathTo(4));
    }

    @Test
    public void testNegativeCycle() {
        BellmanFord bf = new BellmanFord(buildTestInstance3());
        try {
            bf.run(1);
            fail("NegativeCycleException should have been thrown");
        }
        catch (NegativeCycleException e) {}
    }

}
