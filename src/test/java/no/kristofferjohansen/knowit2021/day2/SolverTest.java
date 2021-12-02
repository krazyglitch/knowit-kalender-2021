package no.kristofferjohansen.knowit2021.day2;

import org.junit.jupiter.api.Test;

class SolverTest {

    @Test
    void testSimple() {
        String[][] input = new String[3][2];
        input[0] = new String[] {"Equator", "Point(0.0 0.0)"};
        input[1] = new String[] {"South Pole", "Point(0.0 -90.0)"};
        input[2] = new String[] {"Somewhere", "Point(-150.35 64.12)"};

        int result = Solver.solve(input, true);
    }
}