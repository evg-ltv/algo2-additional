package org.coursera.algo2.huffman.tree;

/**
 * Leaf Node of Huffman Tree
 */
public final class LeafNode extends HuffmanTreeNode {

    private final String symbol;

    public LeafNode(double probability, String symbol) {
        super(probability);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
