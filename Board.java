import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public final class Board {
    private final int[][] tiles;
    //public final int man = this.manhattan();
    //public final int ham = this.hamming();

    public Board(int[][] tiles) {
        this.tiles = new int[tiles[0].length][tiles.length];
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                this.tiles[row][col] = tiles[row][col];
            }
        }
    }

    public String toString() {
        String line = this.dimension() + "\n";
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                line = line.concat(this.tiles[row][col] + " ");
            }
            line = line.concat("\n");
        }
        return line;
    }

    public int dimension() {
        return this.tiles.length;
    }

    public int hamming() {
        int ham = 0;
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                if (this.tiles[row][col] != row * this.dimension() + col + 1) {
                    ham = ham + 1;
                }
            }
        }
        return ham - 1;
    }

    public int manhattan() {
        int man = 0;
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                if (this.tiles[row][col] != row * this.dimension() + col + 1 && this.tiles[row][col] != 0) {
                    int rowNeed = (tiles[row][col] - 1) / this.dimension();
                    int colNeed = tiles[row][col] - 1 - rowNeed * this.dimension();
                    man = man + Math.abs(rowNeed - row) + Math.abs(colNeed - col);
                }
            }
        }
        return man;
    }

    public boolean isGoal() {
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                if (row == this.dimension() - 1 && col == this.dimension() - 1) {
                    if (this.tiles[row][col] == 0) {
                        return true;
                    }
                }
                if (this.tiles[row][col] != row * this.dimension() + col + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        Board b = (Board) y;

        if(this.dimension() != b.dimension()){
            return false;
        }
        for (int row = 0; row < this.dimension(); row++) {
            for (int col = 0; col < this.dimension(); col++) {
                if (this.tiles[row][col] != b.tiles[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neigh = new ArrayList<Board>();
        int rowZ = 0;
        int colZ = 0;
        int row = 0;
        int col = 0;
        for (row = 0; row < this.dimension(); row++) {
            for (col = 0; col < this.dimension(); col++) {
                if (this.tiles[row][col] == 0) {
                    break;
                }
            }
        }
        for (row = 0; row < this.dimension(); row++) {
            for (col = 0; col < this.dimension(); col++) {
                if (this.tiles[row][col] == 0) {
                    rowZ = row;
                    colZ = col;
                }
            }
        }

        if (rowZ - 1 >= 0) {
            Board nBoard = new Board(this.tiles);
            swap(nBoard, rowZ, colZ, rowZ - 1, colZ);
            neigh.add(nBoard);
        }
        if (rowZ + 1 < this.dimension()) {
            Board nBoard = new Board(this.tiles);
            swap(nBoard, rowZ, colZ, rowZ + 1, colZ);
            neigh.add(nBoard);
        }
        if (colZ - 1 >= 0) {
            Board nBoard = new Board(this.tiles);
            swap(nBoard, rowZ, colZ, rowZ, colZ - 1);
            neigh.add(nBoard);
        }
        if (colZ + 1 < this.dimension()) {
            Board nBoard = new Board(this.tiles);
            swap(nBoard, rowZ, colZ, rowZ, colZ + 1);
            neigh.add(nBoard);
        }

        return neigh;
    }

    private void swap(Board y, int row1, int col1, int row2, int col2) {
        int temp = y.tiles[row1][col1];
        y.tiles[row1][col1] = y.tiles[row2][col2];
        y.tiles[row2][col2] = temp;
    }

    public Board twin() {
        Board twinBoard = new Board(this.tiles);
        if(twinBoard.tiles[0][0] != 0) {
            if(twinBoard.tiles[0][1] != 0){
                swap(twinBoard, 0, 0, 0, 1);
            } else {
                swap(twinBoard, 0, 0, 1, 0);
            }
        } else {
            swap(twinBoard, 0, 1, 1, 1);
        }
        /*
        boolean isChanged = false;
        int row;
        int col;

        do {
            row = StdRandom.uniform(0, twinBoard.dimension());
            col = StdRandom.uniform(0, twinBoard.dimension());
        } while (twinBoard.tiles[row][col] == 0);


        while (!isChanged) {
            int direction = StdRandom.uniform(0, 4);
            switch (direction) {
                case 0: {
                    if (col + 1 < twinBoard.dimension() && twinBoard.tiles[row][col + 1] != 0) {
                        swap(twinBoard, row, col, row, col + 1);
                        isChanged = true;
                    }
                    break;
                }
                case 1: {
                    if (col - 1 >= 0 && twinBoard.tiles[row][col - 1] != 0) {
                        swap(twinBoard, row, col, row, col - 1);
                        isChanged = true;
                    }
                    break;
                }
                case 2: {
                    if (row - 1 >= 0 && twinBoard.tiles[row - 1][col] != 0) {
                        swap(twinBoard, row, col, row - 1, col);
                        isChanged = true;
                    }
                    break;
                }
                case 3: {
                    if (direction == 3 && row + 1 < twinBoard.dimension() && twinBoard.tiles[row + 1][col] != 0) {
                        swap(twinBoard, row, col, row + 1, col);
                        isChanged = true;
                    }
                    break;
                }
            }
        }
*/
        final Board twinB = new Board(twinBoard.tiles);
        return twinB;
    }
}
