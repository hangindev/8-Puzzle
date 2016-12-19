## 8 Puzzle
### Assignment 4 for Algorithms - Princeton | Coursera

#### Intro
A program for solving the 8-puzzle problem using priority queue and the A* search algorithm. 

#### Methodology
> We define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. Then, delete from the priority queue the search node with the minimum priority, and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node). Repeat this procedure until the search node dequeued corresponds to a goal board.

#### Reference

http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html

