package org.coursera.algo2.wis;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestWIS {

    /**
     * In a path {1, 4, 5, 4} maximum weight independent set is {4, 4}
     * and sum of its weights is 8
     */
    @Test
    public void testCalculateMaximumWeight() {
        int[] path = new int[] {1, 4, 5, 4};
        WIS wis = new WIS(path);
        assertEquals(8, wis.calculateMaximumWeight());
    }

    /**
     * In a path {1, 4, 5, 4} maximum weight independent set is {4, 4}
     * and sum of its weights is 8
     */
    @Test
    public void testReconstructIndependentSet1() {
        int[] path = new int[] {1, 4, 5, 4};
        WIS wis = new WIS(path);
        wis.calculateMaximumWeight();
        List<Integer> is = wis.reconstructIndependentSet();
        assertEquals(2, is.size());
        assertEquals(4, (int) is.get(0));
        assertEquals(4, (int) is.get(1));
    }

    /**
     * In a path {4, 3, 2, 1} maximum weight independent set is {4, 2}
     * and sum of its weights is 6
     */
    @Test
    public void testReconstructIndependentSet2() {
        int[] path = new int[] {4, 3, 2, 1};
        WIS wis = new WIS(path);
        wis.calculateMaximumWeight();
        List<Integer> is = wis.reconstructIndependentSet();
        assertEquals(2, is.size());
        assertTrue((is.get(0) == 2 && is.get(1) == 4) ^ (is.get(0) == 4 && is.get(1) == 2));
    }

    /**
     * Test WIS methods with a single element path
     */
    @Test
    public void testWISWithSingleElement() {
        int[] path = new int[] {3};
        WIS wis = new WIS(path);
        assertEquals(3, wis.calculateMaximumWeight());
        List<Integer> is = wis.reconstructIndependentSet();
        assertEquals(1, is.size());
        assertEquals(3, (int) is.get(0));
    }

}
