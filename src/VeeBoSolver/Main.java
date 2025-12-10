package VeeBoSolver;
import VeeBoSolver.Solver.*;
import VeeBoSolver.filereader.*;
import VeeBoSolver.Solver.*;
import java.util.Arrays;

class Main
{
    public static void main(String[] args)
    {
        int[][] grid = Reader.buildMatrix("./MazeFiles/m15_15.txt");
        for(int[] i : grid)
        {
            System.out.println(Arrays.toString(i));
        }
        // Graph g = grid.graph;
        // System.out.println("StartID = " + g.getStartID());
        // System.out.println("GoalID = " + g.getGoalID());
        // g.PrintMaze(grid);

        Astar solver = new Astar();
        int ans = solver.solve(grid);
        System.out.println("Min distance: "+ ans);


        // Dijkstra solveDijkstra = new Dijkstra();
        // Long ansDijkstra = solveDijkstra.dijkstra(g, g.getStartID(), g.getGoalID());
        // System.out.println("\nMin distance: "+ ansDijkstra);
        // g.PrintPath(md, g.getPath());

        Greedy solveGreedy = new Greedy();
        int ansGreedy = solveGreedy.greedy(grid, grid[1][1], grid[grid.length-2][grid.length-2]);
        System.out.println("\nMin distance(Greedy): "+ ansGreedy);
        // g.PrintPath(md, g.getPath());
    }
}