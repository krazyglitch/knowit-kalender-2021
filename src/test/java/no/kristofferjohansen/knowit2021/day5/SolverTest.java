package no.kristofferjohansen.knowit2021.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testSimple() {
        String input = "Aurora(Toralv(Grinch(Kari Robinalv) Alvborg) Grinch(Alva(Alve-Berit Anna) Grete(Ola Hans)))";
        assertEquals(2, Solver.solve(input));
    }
}