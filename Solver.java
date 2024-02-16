import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;

public final class Solver {
    private final MinPQ<SearchNode> boardQueue = new MinPQ<SearchNode>();
    private final Stack<Board> solutionSeq = new Stack<Board>();


    private class SearchNode implements Comparable<SearchNode>{

        private SearchNode previousSearchNode;
        private int moves;
        private Board searchBoard;
        private int priotiryFunction;

        public SearchNode(Board searchBoard, SearchNode previousNode, int previousMoves){
            this.searchBoard = searchBoard;
            this.previousSearchNode = previousNode;
            this.moves = previousMoves;
            priotiryFunction = this.searchBoard.manhattan() + this.moves;
        }

        public int compareTo(SearchNode y){
            return this.priotiryFunction - y.priotiryFunction;
        }
    }

    public Solver(Board initial) {

        if (initial == null) throw new IllegalArgumentException();
        SearchNode init = new SearchNode(initial, null, 0);
        boardQueue.insert(init);
        SearchNode minBoard = boardQueue.min();

        while (!minBoard.searchBoard.isGoal()) {
            boardQueue.delMin();
            for (Board neighb: minBoard.searchBoard.neighbors()) {
                if (minBoard.moves == 0){
                    boardQueue.insert(new SearchNode(neighb, minBoard, minBoard.moves + 1));
                } else if (!neighb.equals(minBoard.previousSearchNode.searchBoard)) {
                    boardQueue.insert(new SearchNode(neighb, minBoard, minBoard.moves + 1));
                }
                //boardQueue.insert(new SearchNode(neighb, minBoard, minBoard.moves + 1));
            }
            minBoard = boardQueue.min();
        }
    }

    public boolean isSolvable() {
        return true;
    }


    public int moves() {
        //if (!isSolvable()) return -1;
        return boardQueue.min().moves;
    }

    public Iterable<Board> solution() {
        SearchNode start = boardQueue.min();
        //System.out.println(start.searchBoard);
        while(start.previousSearchNode != null){
            solutionSeq.push(start.searchBoard);
            //System.out.println(start.searchBoard);
            start = start.previousSearchNode;
        }
        solutionSeq.push(start.searchBoard);
        return solutionSeq;
    }

}
