package org.coursera.algo2.sequence;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSequenceAlignment {

    /**
     * The best alignment for two strings AGGGCT and AGGCA is
     * AGGGCT
     * AGG CA
     * Which has one gap and one mismatch
     */
    @Test
    public void testCalculateSimilarity() {
        SequenceAlignment sa = new SequenceAlignment("AGGGCT", "AGGCA");
        assertEquals(2, sa.calculateSimilarity());
        assertEquals(4, sa.calculateSimilarity(2, 2));
    }

    /**
     * Test with completely different strings
     */
    @Test
    public void testCalculateSimilarityAllDiff() {
        SequenceAlignment sa = new SequenceAlignment("ABCD", "EFGH");
        assertEquals(4, sa.calculateSimilarity());
    }

    /**
     * Test with the same strings
     */
    @Test
    public void testCalculateSimilaritySameStrings() {
        SequenceAlignment sa = new SequenceAlignment("ABCD", "ABCD");
        assertEquals(0, sa.calculateSimilarity());
    }

    /**
     * Tests where one or two strings have 0 length
     */
    @Test
    public void testCalculateSimilarityZeroLength() {
        SequenceAlignment sa;

        sa = new SequenceAlignment("", "");
        assertEquals(0, sa.calculateSimilarity());

        sa = new SequenceAlignment("ABCD", "");
        assertEquals(4, sa.calculateSimilarity());

        sa = new SequenceAlignment("", "EFGH");
        assertEquals(4, sa.calculateSimilarity());
    }

}
