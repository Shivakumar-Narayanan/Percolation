import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private boolean[][] a;  //n * n grid
    private final int n;    //grid side length
    private int opencount;  //number of open sites

    //union find data structure to test for percolation
    private final WeightedQuickUnionUF ufPercolation;

    //union find data structure to test for fullness of a cell
    //(does not take into account the sink at the bottom)
    private final WeightedQuickUnionUF ufFullness;
    
    // creates n - by - n grid ,  with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)  throw new IllegalArgumentException("size <= 0");

        a = new boolean[n][n];
        this.n = n;

        ufPercolation = new WeightedQuickUnionUF((n*n) + 2);
        ufFullness = new WeightedQuickUnionUF((n*n) + 1);
        opencount = 0;
    }

    // returs the id of a row col pair
    private int id(int row, int col)
    {
        return n*(row - 1)  +  (col);
    }

    // opens the site (row ,  col) if it is not open already
    public void open(int row,  int col)
    {
        if (row < 1 || col < 1 || row > a.length || col > a.length)  throw new IllegalArgumentException("out of range");
        
        if(isOpen()row, col)    return;

        int id = id(row, col);
        opencount++;

        int neighbourId, r, c;

        // connect the cell with the top cell
        r = row - 1;
        c = col;
        if (!(r < 1 || r > n ||  c > n) && a[r - 1][c - 1])
        {
            neighbourId = id(r, c);
            ufPercolation.union(id, neighbourId);
            ufFullness.union(id, neighbourId);
        }

        // connect the cell with the bottom cell
        r = row + 1;
        c = col;
        if (!(r < 1 || r > n ||  c > n) && a[r - 1][c - 1])
        {
            neighbourId = id(r, c);
            ufPercolation.union(id, neighbourId);
            ufFullness.union(id, neighbourId);
        }

        // connect the cell with the right cell
        r = row;
        c = col + 1;
        if (!(r > n || c < 1 || c > n) && a[r - 1][c - 1])
        {
            neighbourId = id(r, c);
            ufPercolation.union(id, neighbourId);
            ufFullness.union(id, neighbourId);
        }

        // connect the cell with the left cell
        r = row;
        c = col - 1;
        if (!(r > n || c < 1 || c > n) && a[r - 1][c - 1])
        {
            neighbourId = id(r, c);
            ufPercolation.union(id, neighbourId);
            ufFullness.union(id, neighbourId);
        }

        //if the cur cell is in row 1, then connect it to the source
        if (row == 1)
        {
            ufPercolation.union(id, 0);
            ufFullness.union(id, 0);
        }

        //if the cur cell is in the last row, connect it with the sink
        if (row == n)
        {
            ufPercolation.union(id, (n*n) + 1);
        }
    }

    // is the site (row , col) open?
    // a cell is open if it allows percolation through it
    public boolean isOpen(int row, int col)
    {
        if (row < 1 || col < 1 || row > a.length || col > a.length)  throw new IllegalArgumentException("out of range");

        return a[row - 1][col - 1];
    }

    // is the site (row , col) full?
    // a cell is full if it allows percolation through it and if
    // the percolating material has percolated to it from the source
    public boolean isFull(int row, int col)
    {
        if (row < 1 || col < 1 || row > a.length || col > a.length)  throw new IllegalArgumentException("out of range");

        //cannot use ufPercolation to see if source and cell are connected
        //reason : we would allow backflow to occur
        // backflow :- source -> sink, then sink -> cell
        return isOpen(row, col) && (ufFullness.find(0) == ufFullness.find(id(row, col)));
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return opencount;
    }

    // does the system percolate?
    public boolean percolates()
    {
        //if the source is connected to the sink, return true
        return ufPercolation.find(0) == ufPercolation.find((n*n) + 1);
    }

    // test client (optional)
    public static void main(String[] args)
    {
        // this is a empty method
    }
}
