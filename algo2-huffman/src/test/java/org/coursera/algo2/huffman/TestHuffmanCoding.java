package org.coursera.algo2.huffman;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class TestHuffmanCoding {

    @Test
    public void testCodes() {
        Map<String, Double> symbols = new HashMap<String, Double>();
        symbols.put("A", 0.60);
        symbols.put("B", 0.25);
        symbols.put("C", 0.10);
        symbols.put("D", 0.05);
        Map<String, String> codes = HuffmanCoding.calculateCodes(symbols);
        assertEquals(1, codes.get("A").length());
        assertEquals(2, codes.get("B").length());
        assertEquals(3, codes.get("C").length());
        assertEquals(3, codes.get("D").length());
    }

}
