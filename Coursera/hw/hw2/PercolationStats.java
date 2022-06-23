/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;
    private double[] arr;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException("Trials should larger than 0");
        }
        this.trials = trials;
        this.arr = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                p.open(row, col);
            }
            arr[i] = p.numberOfOpenSites() / (double) (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(arr);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(arr);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trails);
        System.out.println("mean                    = "
                                   + percolationStats.mean());
        System.out.println("stddev                  = "
                                   + percolationStats.stddev());
        System.out.println("95% confidence interval = ["
                                   + percolationStats.confidenceLo() + ", "
                                   + percolationStats.confidenceHi() + "]");

    }

}
