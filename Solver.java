import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private MinPQ<SearchNode> pq;
    private MinPQ<SearchNode> pqTwin;
    private Board initial;
    private int n;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private int priority;
        private SearchNode previousNode;

        public SearchNode(Board board, int moves, SearchNode previousNode) {
            this.board = board;
            this.moves = moves;
            if (board.isGoal()) priority = 0;
            else priority = moves + board.manhattan();
            this.previousNode = previousNode;
        }

        public int compareTo(SearchNode that) {
            if (this.priority != that.priority) return (this.priority - that.priority);
            else return (that.moves - this.moves);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        n = initial.dimension();
        pq = new MinPQ<SearchNode>();
        pqTwin = new MinPQ<SearchNode>();

        /**
         SearchNode minNode;
         //Initialize
         pq.insert(new SearchNode(initial, 0, null));
         //StdOut.println("------Solver-------");  //test
         while(!pq.min().board.isGoal()) {
         minNode = pq.min();
         //StdOut.println("---->minNode.board");  //test
         //StdOut.println(minNode.board);  //test
         //StdOut.println("moves= "+pq.min().moves);  //test
         //StdOut.println("manhattan= "+pq.min().board.manhattan());  //test
         //StdOut.println("priority= "+pq.min().priority);  //test

         for (Board neighbor: minNode.board.neighbors()) {
         //StdOut.println("---->neighbor");  //test
         if (minNode.moves==0) {
         pq.insert(new SearchNode(neighbor, minNode.moves+1, minNode));
         //StdOut.println(neighbor);  //test
         //StdOut.println("moves= "+(minNode.moves+1));  //test
         //StdOut.println("priority= "+(minNode.moves+1+neighbor.manhattan()));  //test
         }
         else if (!neighbor.equals(minNode.previousNode.board)) {
         pq.insert(new SearchNode(neighbor, minNode.moves+1, minNode));
         //StdOut.println(neighbor);  //test
         //StdOut.println("moves= "+(minNode.moves+1));  //test
         //StdOut.println("priority= "+(minNode.moves+1+neighbor.manhattan()));  //test
         }
         }
         }
         StdOut.println("----------->pq.size()= "+pq.size());//test
         */
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        SearchNode minNode;
        SearchNode minNodeTwin;
        pq.insert(new SearchNode(initial, 0, null));
        pqTwin.insert(new SearchNode(initial.twin(), 0, null));
        while(!pq.min().board.isGoal() && !pqTwin.min().board.isGoal()) {
            minNode = pq.min();
            minNodeTwin = pqTwin.min();
            for (Board neighbor: minNode.board.neighbors()) {
                if (minNode.moves==0) {
                    pq.insert(new SearchNode(neighbor, minNode.moves+1, minNode));
                }
                else if (!neighbor.equals(minNode.previousNode.board)) {
                    pq.insert(new SearchNode(neighbor, minNode.moves+1, minNode));
                }
            }
            // Twin
            for (Board neighbor: minNodeTwin.board.neighbors()) {
                if (minNodeTwin.moves==0) {
                    pqTwin.insert(new SearchNode(neighbor, minNodeTwin.moves+1, minNodeTwin));
                }
                else if (!neighbor.equals(minNodeTwin.previousNode.board)) {
                    pqTwin.insert(new SearchNode(neighbor, minNodeTwin.moves+1, minNodeTwin));
                }
            }
        }
        if (pq.min().priority==0) {
            return true;
        }
        if (pqTwin.min().priority==0) {
            return false;
        }
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if(!isSolvable()) return -1;
        return pq.min().moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    // -FILO???
    public Iterable<Board> solution() {
        if(!isSolvable()) return null;
        Stack<Board> stackSolution = new Stack<Board>();
        SearchNode current = pq.min();
        while (current.previousNode!=null) {
            stackSolution.push(current.board);
            current = current.previousNode;
        }
        stackSolution.push(initial);
        return stackSolution;
    }

    // solve a slider puzzle
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        //solver.isSolvable(); //test

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
