package no.kristofferjohansen.knowit2021.day9;

import java.util.Date;
import java.util.HashMap;

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

    static long solve() {
        long presentLimit = 2000;
        long[][] history = new long[3][];
        history[0] = new long[] {1854803357L, 2424154637L};
        history[1] = new long[] {2787141611L, 2807727397L};
        history[2] = new long[] {1159251923L, 2537380333L};

        HashMap<Long, Integer> presentCountMap = new HashMap<>();
        for (long[] event : history) {
            long remainingPresents = event[0];
            long childrenCount = event[1];

            for (long presentsPerChild = presentLimit; presentsPerChild > 0; presentsPerChild--) {
                long totalPresents = childrenCount * presentsPerChild + remainingPresents;

                if (presentCountMap.containsKey(totalPresents)) {
                    if (presentCountMap.get(totalPresents) == 2) {
                        return totalPresents;
                    }

                    presentCountMap.put(totalPresents, presentCountMap.get(totalPresents)+1);
                } else {
                    presentCountMap.put(totalPresents, 1);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        new Solver();
    }
}
