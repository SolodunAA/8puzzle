import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Board initial = new Board(tiles);
       // System.out.println(initial.toString());

        //System.out.println(initial.toString());
        //System.out.println("dim = " + check.dimension());
        //System.out.println("hamming = " + check.hamming());
        //System.out.println("manhattan = " + check.manhattan());
        //check.neighbors();
        //for(Board m: check.neighbors()){
            //System.out.println(m.toString());
        //}
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }
}
