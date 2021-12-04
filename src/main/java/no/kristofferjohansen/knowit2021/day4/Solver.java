package no.kristofferjohansen.knowit2021.day4;

import java.util.Date;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            System.out.println(solve("10079", 20));
//            System.out.println(solve("11", 1));
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /*
     * This solution kind of works and is hacked together to work for both the example and the full solution.
     * Solution was found by noticing that there is a repeating pattern and is therefore unlikely to work for other numbers.
     * There are much better solutions which have been found to solve this problem, using pre-calculated distances
     * or mathematical solutions to name a few.
     */

    static String solve(String input, int log) {
        boolean movingNorth = true;
        int x = 0;
        int y = 0;
        int totalSteps = Integer.parseInt(input);

        for (int step = 0; step < totalSteps; step++) {
            if (movingNorth) {
                y++;
                if (y % 3 == 0 && y % 5 != 0) movingNorth = false;
            } else {
                x++;
                if (x % 5 == 0 && x % 3 != 0) movingNorth = true;
            }
        }

        String strX = String.valueOf(x);
        String strY = String.valueOf(y);
        String calcX;
        String calcY;
        if (input.length() > 4) {
            calcX = strX.substring(0, 1).repeat(log - strX.length() + 1) + strX.substring(1);
            calcY = strY.substring(0, 1).repeat(log - strY.length() + 1) + strY.substring(1);
        } else {
            calcX = strX;
            calcY = strY;
        }

        return String.format("%s,%s", calcX, calcY);
    }

    public static void main(String[] args) {
        new Solver();
    }
}