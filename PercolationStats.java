import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

/*
    * explanation of the statistical measures calculated below has been
      explained in the readme document
 */

public class PercolationStats
{
    private static final double CON = 1.96; //z* value for a 95 % confiddence interval
    private final int n, t;
    private double mean, stddev, conlo, conhi;
    private double[] results;

    // perform independent trials on an n - by - n grid
    public PercolationStats(int n, int trials)
    {
        this.n = n;
        t = trials;
        if (n < 1 || t < 1)
        {
            throw new IllegalArgumentException("n ot t less than 0");
        }
        mean = 0;
        stddev = 0;
        conlo = 0;
        conhi = 0;
        results = new double[t];

        for (int i = 0; i < t; i++)
        {
            this.compute(i);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        mean = StdStats.mean(results);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        stddev = StdStats.stddev(results);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        if(mean == 0)
        {
            mean();
        }
        if(stddev == 0)
        {
            stddev();
        }
        return mean - ((CON*stddev)/Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        if(mean == 0)
        {
            mean();
        }
        if(stddev == 0)
        {
            stddev();
        }
        return mean + ((CON*stddev)/Math.sqrt(t));
    }

    // computes the percolation threshold for trial #i and stores it in resuls[i]
    private void compute(int i)
    {
        double count = 0;
        Percolation p = new Percolation(n);

        //pick a random cell and open it if not open.
        //stop when the system percolates
        while (!p.percolates())
        {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            if (!p.isOpen(row, col))
            {
                count++;
                p.open(row, col);
            }
        }

        double frac = count/(n*n);
        results[i] = frac;
    }
    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats pstats = new PercolationStats(n, t);

        System.out.println("mean                    = " +   pstats.mean);
        System.out.println("stddev                  = " + pstats.stddev);
        System.out.println("95% confidence interval = [" + pstats.confidenceLo() + ", " + pstats.confidenceHi() + "]");
    }

}
