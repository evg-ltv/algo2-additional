package org.coursera.algo2.huffman.tree;

/**
 * Huffman Tree Generic Node
 * Implements Comparable interface using Probability as a key
 */
public abstract class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    private final double probability;

    public HuffmanTreeNode(double probability) {
        if (Double.isNaN(probability) || probability < 0 || probability > 1) {
            throw new IllegalArgumentException("probability should be between 0 and 1");
        }
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    @Override
    public int compareTo(HuffmanTreeNode that) {
        return Double.compare(this.probability, that.probability);
    }

}
