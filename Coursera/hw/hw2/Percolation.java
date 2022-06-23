/* *****************************************************************************
 *  Name:              Jiawei Lu
 *  Coursera User ID:  123456
 *  Last modified:     06/16/2022
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] map;
    private int n;
    private WeightedQuickUnionUF ufPer;
    private WeightedQuickUnionUF ufFull;
    private int numOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N should be larger than 0.");
        }
        this.n = n;
        this.map = new int[n][n];
        this.ufFull = new WeightedQuickUnionUF(n * n + 1);
        this.ufPer = new WeightedQuickUnionUF(n * n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index out of boundary.");
        }

        if (map[row - 1][col - 1] == 0) {
            map[row - 1][col - 1] = 1;
            numOpenSites++;

            if (row == 1) {
                ufPer.union(col, 0);
                ufFull.union(col, 0);
            }

            if (row == n) {
                ufPer.union((n - 1) * n + col, n * n + 1);
            }

            if (row > 1 && isOpen(row - 1, col)) {
                ufFull.union((row - 1) * n + col, (row - 2) * n + col);
                ufPer.union((row - 1) * n + col, (row - 2) * n + col);
            }

            if (row < n && isOpen(row + 1, col)) {
                ufFull.union((row - 1) * n + col, row * n + col);
                ufPer.union((row - 1) * n + col, row * n + col);
            }

            if (col > 1 && isOpen(row, col - 1)) {
                ufPer.union((row - 1) * n + col, (row - 1) * n + col - 1);
                ufFull.union((row - 1) * n + col, (row - 1) * n + col - 1);
            }

            if (col < n && isOpen(row, col + 1)) {
                ufPer.union((row - 1) * n + col, (row - 1) * n + col + 1);
                ufFull.union((row - 1) * n + col, (row - 1) * n + col + 1);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index out of boundary.");
        }

        return map[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index out of boundary.");
        }

        return ufFull.find(0) == ufFull.find((row - 1) * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufPer.find(0) == ufPer.find(n * n + 1);
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 2);

        System.out.println(percolation.isFull(1, 2));
        System.out.println(percolation.percolates());
    }
}

