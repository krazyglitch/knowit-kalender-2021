package no.kristofferjohansen.knowit2021.day10;

import java.util.Date;

public class Solver {

    public Solver() {
        try {
            Date start = new Date();
            System.out.println(solve(D, 8));
            System.out.printf("Regular solution took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solve(int start, int points) {
        boolean[] visited = new boolean[9];
        visited[start] = true;

        return calculatePaths(start, setupBlockingKeys(), visited, points-1);
    }

    static int calculatePaths(int position, int[][] blockingKeys, boolean[] visited, int movesLeft) {
        if (movesLeft == 0) {
            return 1;
        }

        int sum = 0;
        visited[position] = true;

        for (int i = 0; i < 9; i++) {
            if (!visited[i] && (blockingKeys[i][position] == 0 || visited[blockingKeys[i][position]])) {
                sum += calculatePaths(i, blockingKeys, visited, movesLeft-1);
            }
        }

        visited[position] = false;
        return sum;
    }

    static final int    A = 0, B = 1, C = 2,
                        D = 3, E = 4, F = 5,
                        G = 6, H = 7, I = 8;

    /*
     *  A   B   C   ||  0   1   2
     *              ||
     *  D   E   F   ||  3   4   5
     *              ||
     *  G   H   I   ||  6   7   8
     *
     *  Blocking key indicates a key which blocks movement between i,j
     *
     */

    static private int[][] setupBlockingKeys() {
        int[][] blockingKeys = new int[9][9];
        fillBlockingKeys(blockingKeys, A, B, C);
        fillBlockingKeys(blockingKeys, D, E, F);
        fillBlockingKeys(blockingKeys, G, H, I);
        fillBlockingKeys(blockingKeys, A, D, G);
        fillBlockingKeys(blockingKeys, B, E, H);
        fillBlockingKeys(blockingKeys, C, F, I);
        fillBlockingKeys(blockingKeys, A, E, I);
        fillBlockingKeys(blockingKeys, C, E, G);

        return blockingKeys;
    }

    static private void fillBlockingKeys(int[][] blockingKeys, int x, int blockingKeyIdx, int y) {
        blockingKeys[x][y] = blockingKeys[y][x] = blockingKeyIdx;
    }

    public static void main(String[] args) {
        new Solver();
    }
}
