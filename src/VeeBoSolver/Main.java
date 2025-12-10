package VeeBoSolver;
import VeeBoSolver.Solver.*;
import VeeBoSolver.filereader.*;
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
        // Graph g = md.graph;
        // System.out.println("StartID = " + g.getStartID());
        // System.out.println("GoalID = " + g.getGoalID());
        // g.PrintMaze(md);

        Astar solver = new Astar();
        int ans = solver.solve(grid);
        System.out.println("Min distance: "+ ans);


        // Dijkstra solveDijkstra = new Dijkstra();
        // Long ansDijkstra = solveDijkstra.dijkstra(g, g.getStartID(), g.getGoalID());
        // System.out.println("\nMin distance: "+ ansDijkstra);
        // g.PrintPath(md, g.getPath());

        // Greedy solveGreedy = new Greedy();
        // Long ansGreedy = solveGreedy.greedy(g, g.getStartID(), g.getGoalID());
        // System.out.println("\nMin distance(Greedy): "+ ansGreedy);
        // g.PrintPath(md, g.getPath());
    }
}