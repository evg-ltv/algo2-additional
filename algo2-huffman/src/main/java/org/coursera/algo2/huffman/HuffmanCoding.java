package org.coursera.algo2.huffman;

import org.coursera.algo2.huffman.tree.HuffmanTreeNode;
import org.coursera.algo2.huffman.tree.InternalNode;
import org.coursera.algo2.huffman.tree.LeafNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Huffman coding algorithm
 */
public class HuffmanCoding {

    /**
     * Creates HuffmanTree
     * @param symbols probabilities of symbols
     * @return root node of the tree
     */
    public static HuffmanTreeNode createHuffmanTree(Map<String, Double> symbols) {
        if (symbols == null || symbols.size() == 0) {
            throw new IllegalArgumentException("symbols must contain at least 1 symbol");
        }
        PriorityQueue<HuffmanTreeNode> minHeap = new PriorityQueue<HuffmanTreeNode>();
        for (Map.Entry<String, Double> entry : symbols.entrySet()) {
            minHeap.add(new LeafNode(entry.getValue(), entry.getKey()));
        }
        while (minHeap.size() > 1) {
            HuffmanTreeNode leftNode = minHeap.poll(), rightNode = minHeap.poll();
            double internalNodeProbability = leftNode.getProbability() + rightNode.getProbability();
            minHeap.add(new InternalNode(internalNodeProbability, leftNode, rightNode));
        }
        return minHeap.poll();
    }

    /*
     * Does recursive traversal of Huffman Tree to generate Huffman Codes
     */
    private static void recursiveTraversal(HuffmanTreeNode node, Map<String, String> codes,
                                           int position, char[] code) {
        if (node instanceof InternalNode) {
            InternalNode currentNode = (InternalNode) node;
            code[position] = '0';
            recursiveTraversal(currentNode.getLeftNode(), codes, position + 1, code);
            code[position] = '1';
            recursiveTraversal(currentNode.getRightNode(), codes, position + 1, code);
        }
        else if (node instanceof LeafNode) {
            LeafNode currentNode = (LeafNode) node;
            StringBuilder sb = new StringBuilder(position);
            for (int idx = 0; idx < position; idx++) {
                sb.append(code[idx]);
            }
            codes.put(currentNode.getSymbol(), sb.toString());
        }
    }

    /**
     * Returns a map of symbols and their codes
     * For simplicity, codes are strings of 0 and 1
     * @param symbols probabilities of symbols
     * @return map - symbol, its code
     */
    public static Map<String, String> calculateCodes(Map<String, Double> symbols) {
        HuffmanTreeNode rootNode = createHuffmanTree(symbols);
        Map<String, String> codes = new HashMap<String, String>();
        recursiveTraversal(rootNode, codes, 0, new char[symbols.size()]);
        return codes;
    }

}
