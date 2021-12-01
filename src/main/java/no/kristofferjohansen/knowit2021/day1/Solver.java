package no.kristofferjohansen.knowit2021.day1;

import no.kristofferjohansen.knowit2021.common.FileUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            List<String> dataLines = FileUtil.readInputFile(this.getClass());
            System.out.println(solve(dataLines.get(0)));
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solve(String input) {
        List<NumberPart> allNumberParts = new ArrayList<>();
        allNumberParts.add(new NumberPart("femti", 50));
        allNumberParts.add(new NumberPart("førti", 40));
        allNumberParts.add(new NumberPart("tretti", 30));
        allNumberParts.add(new NumberPart("tjue", 20));
        allNumberParts.add(new NumberPart("ti", 10));
        allNumberParts.add(new NumberPart("nitten", 19));
        allNumberParts.add(new NumberPart("atten", 18));
        allNumberParts.add(new NumberPart("sytten", 17));
        allNumberParts.add(new NumberPart("seksten", 16));
        allNumberParts.add(new NumberPart("femten", 15));
        allNumberParts.add(new NumberPart("fjorten", 14));
        allNumberParts.add(new NumberPart("tretten", 13));
        allNumberParts.add(new NumberPart("tolv", 12));
        allNumberParts.add(new NumberPart("elleve", 11));
        allNumberParts.add(new NumberPart("ni", 9));
        allNumberParts.add(new NumberPart("åtte", 8));
        allNumberParts.add(new NumberPart("sju", 7));
        allNumberParts.add(new NumberPart("seks", 6));
        allNumberParts.add(new NumberPart("fem", 5));
        allNumberParts.add(new NumberPart("fire", 4));
        allNumberParts.add(new NumberPart("tre", 3));
        allNumberParts.add(new NumberPart("to", 2));
        allNumberParts.add(new NumberPart("en", 1));

        String data = input;
        int result = 0;
        while (data.length() > 0) {
            for (NumberPart numberPart : allNumberParts) {
                if (data.startsWith(numberPart.getWord())) {
                    result += numberPart.getValue();
                    data = data.substring(numberPart.getWord().length());
                    break;
                }
            }
        }

        return result;

}

    public static void main(String[] args) {
        new Solver();
    }
}

class NumberPart {
    private final String word;
    private final int value;

    public NumberPart(String word, int value) {
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public int getValue() {
        return value;
    }
}