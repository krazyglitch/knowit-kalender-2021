package no.kristofferjohansen.knowit2021.day1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testSimple() {
        String testValue = "entotrefirefem";
        assertEquals(15, Solver.solve(testValue));
    }

    @Test
    void testAdvanced() {
        String testValue = "sjufirenitrettentrettitretrettitre";
        assertEquals(99, Solver.solve(testValue));
    }

    @Test
    void testTricky() {
        String testValue = "trettifemti";
        assertEquals(80, Solver.solve(testValue));
    }
}