package org.coursera.algo2.bellmanford;

/**
 * Thrown when a negative cycle is detected while
 * running the Bellman-Ford algorithm
 */
public class NegativeCycleException extends Exception {

    public NegativeCycleException() {
        super();
    }

}
