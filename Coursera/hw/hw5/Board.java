import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * @author Jiawei Lu
 * @version 1.0
 */

public class Board {
    private int[][] tiles;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(" " + tiles[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt++;
                if (tiles[i][j] == 0) continue;
                if (tiles[i][j] != cnt) {
                    res++;
                }
            }
        }
        return res;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) continue;
                int row = (tiles[i][j] - 1) / n;
                int col = tiles[i][j] - n * row - 1;
                res += Math.abs(i - row) + Math.abs(j - col);
            }
        }
        return res;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board board = (Board) y;
            if (this.n != board.n) {
                return false;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.tiles[i][j] != board.tiles[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        class neighborIter implements Iterator<Board> {
            private Board[] boards;
            private int pos;

            private neighborIter() {
                int r = -1, c = -1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (tiles[i][j] == 0) {
                            r = i;
                            c = j;
                            break;
                        }
                    }
                }
                Board[] dir = new Board[4];
                int idx = 0;
                int[] nums = new int[] { 0, 1, 0, -1, 0 };
                for (int i = 0; i < 4; i++) {
                    int nr = r + nums[i], nc = c + nums[i + 1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        Board board = new Board(tiles);
                        board.tiles[r][c] = board.tiles[nr][nc];
                        board.tiles[nr][nc] = 0;
                        dir[idx++] = board;
                    }
                }
                pos = 0;
                boards = new Board[idx];
                for (int i = 0; i < idx; i++) {
                    boards[i] = dir[i];
                }
            }

            public boolean hasNext() {
                return pos < boards.length;
            }

            public Board next() {
                return boards[pos++];
            }
        }

        return neighborIter::new;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int row1 = StdRandom.uniform(n), col1 = StdRandom.uniform(n);
        int row2 = StdRandom.uniform(n), col2 = StdRandom.uniform(n);
        Board board = new Board(tiles);
        int temp = board.tiles[row1][col1];
        board.tiles[row1][col1] = board.tiles[row2][col2];
        board.tiles[row2][col2] = temp;
        return board;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[][] { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println("Hamming:" + board.hamming());
        System.out.println("Manhattan:" + board.manhattan());
        System.out.println("Goal:" + board.isGoal());
        System.out.println("Neighbors:");
        for (Board b : board.neighbors()) {
            System.out.println(b);
        }

        System.out.println("Twin():" + board.twin());

        int[][] tilesNew = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        Board boardNew = new Board(tilesNew);
        System.out.println("Goal BoardNew:" + boardNew.isGoal());
        System.out.println("Equals:" + board.equals(boardNew));

        int[][] tiles1 = new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
        Board board1 = new Board(tiles1);
        System.out.println(board1.hamming());
        System.out.println(board1.manhattan());

    }

}
