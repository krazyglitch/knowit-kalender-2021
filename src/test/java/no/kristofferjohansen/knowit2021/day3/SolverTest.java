package no.kristofferjohansen.knowit2021.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testSimple() {
        String input = "JJJJJNNJJNNJJJJJ";
        assertEquals("8, 3", Solver.solve(input));
    }
}