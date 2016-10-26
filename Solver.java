import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class Solver {
  private class SearchNode implements Comparable<SearchNode> {
      private Board board;
      private int moves;
      private int priority;
      private SearchNode previousNode;

      public SearchNode(Board board, int moves, SearchNode previousNode) {
          this.board=board;
          this.moves=moves;
          priority=moves+board.hamming();
          this.previousNode=previousNode;
      }

      public int compareTo(SearchNode that) {
          return (this.priority - that.priority);
      }

      public boolean isGoal() {
          return board.isGoal();
      }
  }

    // find a solution to the initial board (using the A* algorithm)
    // -save dimension
    public Solver(Board initial)

        // is the initial board solvable?
        // -check for inversions(+blank row)
        public boolean isSolvable()

        // min number of moves to solve initial board; -1 if unsolvable
        public int moves()

        // sequence of boards in a shortest solution; null if unsolvable
        // -FILO???
        public Iterable<Board> solution()

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
}
