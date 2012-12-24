package org.coursera.algo2.huffman.tree;

/**
 * Internal (not leaf) Node of Huffman Tree
 */
public final class InternalNode extends HuffmanTreeNode {

    private final HuffmanTreeNode leftNode;
    private final HuffmanTreeNode rightNode;

    public InternalNode(double probability, HuffmanTreeNode leftNode, HuffmanTreeNode rightNode) {
        super(probability);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public HuffmanTreeNode getLeftNode() {
        return leftNode;
    }

    public HuffmanTreeNode getRightNode() {
        return rightNode;
    }

}
