package no.kristofferjohansen.knowit2021.day5;

import no.kristofferjohansen.knowit2021.common.FileUtil;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static int solve(String input) {
        Elf headElf = new Elf(input, -1);

        return headElf.getHighestGeneration();
    }

    public static void main(String[] args) {
        new Solver();
    }
}

class Elf {
    static Pattern namePattern = Pattern.compile("^([\\wæøåÆØÅ-]+)");
    private final String name;
    private final int generation;
    private final Elf[] parents;

    public Elf(String input, int prevGeneration) {
        Matcher nameMatcher = namePattern.matcher(input);
        nameMatcher.find();
        this.name = nameMatcher.group(1);

        if (name.equals("Grinch")) {
            generation = prevGeneration;
        } else {
            generation = ++prevGeneration;
        }

        int leftBrackets = 0;
        int rightBrackets = 0;

        if (input.length() > name.length()) {
            String nextGen = input.substring(name.length()+1, input.length()-1);
            parents = new Elf[2];

            int firstBracketIdx = nextGen.indexOf('(');
            int firstSpaceIdc = nextGen.indexOf(' ');
            if (firstBracketIdx == -1 || (firstBracketIdx > nextGen.indexOf(" "))) {
                parents[0] = new Elf(nextGen.substring(0, firstSpaceIdc+1).trim(), generation);
                parents[1] = new Elf(nextGen.substring(firstSpaceIdc+1).trim(), generation);
            } else {
                for (int i = 0; i < nextGen.length(); i++) {
                    if (nextGen.charAt(i) == '(') {
                        leftBrackets++;
                    } else if (nextGen.charAt(i) == ')') {
                        rightBrackets++;
                    }

                    if (leftBrackets > 0 && leftBrackets - rightBrackets == 0) {
                        parents[0] = new Elf(nextGen.substring(0, i+1).trim(), generation);
                        parents[1] = new Elf(nextGen.substring(i + 1).trim(), generation);
                        break;
                    }
                }
            }
        } else {
            parents = new Elf[0];
        }
    }

    public int getGeneration() {
        return generation;
    }

    public int getHighestGeneration() {
        if (parents.length > 0) {
            int highestParentA = parents[0].getHighestGeneration();
            int highestParentB = parents[1].getHighestGeneration();

            return Math.max(highestParentA, highestParentB);
        }

        return getGeneration();
    }

    @Override
    public String toString() {
        return "Elf{" +
                "name='" + name + '\'' +
                ", generation=" + generation +
                '}';
    }
}