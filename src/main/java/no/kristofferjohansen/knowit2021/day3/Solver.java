package no.kristofferjohansen.knowit2021.day3;

import no.kristofferjohansen.knowit2021.common.FileUtil;

import java.util.Date;
import java.util.List;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            List<String> data = FileUtil.readInputFile(this.getClass());

            System.out.println(solve(data.get(0)));
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static String solve(String data) {
        Neighbourhood largestNeighbourHood = new Neighbourhood(-1, -1);
        int sum;
        for (int i = 0; i < data.length()-1; i++) {
            sum = getValue(data.charAt(i));

            for (int j = i+1; j < data.length(); j++) {
                sum += getValue(data.charAt(j));

                if (sum == 0 && (j-i+1) > largestNeighbourHood.getSize()) {
                    largestNeighbourHood.setIndex(i);
                    largestNeighbourHood.setSize(j-i+1);
                }
            }
        }

        return String.format("%d, %d", largestNeighbourHood.getSize(), largestNeighbourHood.getIndex());
    }

    private static int getValue(char house) {
        return house == 'J' ? 1 : -1;
    }

    public static void main(String[] args) {
        new Solver();
    }
}

class Neighbourhood {
    private int index;
    private int size;

    public Neighbourhood(int index, int size) {
        this.index = index;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSize(int size) {
        this.size = size;
    }
}