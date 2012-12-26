package org.coursera.algo2.sequence;

/**
 * Dynamic Programming Algorithm for Sequence Alignment Problem
 * Algorithm calculates Needleman–Wunsch score
 * (similarity measure between two sequences)
 */
public class SequenceAlignment {

    private final String sequence1;
    private final String sequence2;

    /**
     * Construct SequenceAlignment object to run the algorithm
     */
    public SequenceAlignment(String sequence1, String sequence2) {
        if (sequence1 == null || sequence2 == null) {
            throw new IllegalArgumentException("Both sequences must be set");
        }
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
    }

    /*
     * Returns minimum value
     */
    private static int min(int value1, int value2, int value3) {
        return Math.min(value1, Math.min(value2, value3));
    }

    /**
     * Calculates similarity between two sequences
     * @param gapPenalty penalty which is added to the score when a gap must be introduced
     * @param mismatchPenalty penalty which is added to the score when two symbols don't match
     */
    public int calculateSimilarity(int gapPenalty, int mismatchPenalty) {
        if (gapPenalty <= 0 || mismatchPenalty <= 0) {
            throw new IllegalArgumentException("Penalties must be positive");
        }
        int[][] memo = new int[sequence1.length() + 1][sequence2.length() + 1];
        for (int idx = 1; idx <= sequence1.length(); idx++) {
            memo[idx][0] = idx * gapPenalty;
        }
        for (int idx = 1; idx <= sequence2.length(); idx++) {
            memo[0][idx] = idx * gapPenalty;
        }
        for (int idx1 = 1; idx1 <= sequence1.length(); idx1++) {
            for (int idx2 = 1; idx2 <= sequence2.length(); idx2++) {
                int diffPenalty = 0;
                if (sequence1.charAt(idx1 - 1) != sequence2.charAt(idx2 - 1)) {
                    diffPenalty = mismatchPenalty;
                }
                memo[idx1][idx2] = min(memo[idx1 - 1][idx2 - 1] + diffPenalty,
                                       memo[idx1 - 1][idx2] + gapPenalty,
                                       memo[idx1][idx2 - 1] + gapPenalty);
            }
        }
        return memo[sequence1.length()][sequence2.length()];
    }

    /**
     * Calculates similarity between two sequences
     * using default values for penalties:
     * Gap penalty = 1
     * Mismatch penalty = 1
     */
    public int calculateSimilarity() {
        return calculateSimilarity(1, 1);
    }

}
