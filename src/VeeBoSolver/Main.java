package VeeBoSolver;
import VeeBoSolver.Solver.*;
import VeeBoSolver.filereader.*;
import VeeBoSolver.Solver.*;
import java.util.Arrays;

class Main
{
    public static void main(String[] args)
    {
        int[][] grid = Reader.buildMatrix("./MazeFiles/m100_100.txt");
        for(int[] i : grid)
        {
            System.out.println(Arrays.toString(i));
        }

        Astar solver = new Astar();
        int ans = solver.solve(grid);
        System.out.println("Min distance: " + ans);

        System.out.println();

        Dijkstra solver2 = new Dijkstra();
        int ans2 = solver2.dijkstra(grid);
        System.out.println("Min distance: " + ans2);

        System.out.println();

        Greedy solve3 = new Greedy();
        int ans3 = solve3.greedy(grid);
        System.out.println("\nMin distance: " + ans3);
    }
}