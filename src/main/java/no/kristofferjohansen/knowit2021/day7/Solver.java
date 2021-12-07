package no.kristofferjohansen.knowit2021.day7;

import java.util.Date;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            System.out.println(solve());
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static String solve() {
        double santa = 20.0;
        double ant = 1.0;

        while (ant < santa) {
            double newLength = santa+20.0;

            ant *= newLength/santa;
            ant++;

            santa = newLength;
        }

        return String.format("%.0f", santa/100.0);
    }

    public static void main(String[] args) {
        new Solver();
    }
}
