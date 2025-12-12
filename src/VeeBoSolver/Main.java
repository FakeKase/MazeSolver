package VeeBoSolver;
import VeeBoSolver.Solver.*;
import VeeBoSolver.Solver.GA.*;
import VeeBoSolver.filereader.*;
import java.util.*;

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows and columns: ");
        String row = sc.next();
        String column = sc.next();
        int[][] grid = Reader.buildMatrix("./MazeFiles/m"+row+"_"+column+".txt");
        for(int[] i : grid)
        {
            System.out.println(Arrays.toString(i));
        }

        evolution ev = new evolution();
        ev.solve(grid);

        Astar solver = new Astar();
        int ans = solver.solve(grid);
        System.out.println("Min distance: " + ans);

        System.out.println();

        // Dijkstra solver2 = new Dijkstra();
        // int ans2 = solver2.dijkstra(grid);
        // System.out.println("Min distance: " + ans2);

        // System.out.println();

        // Greedy solve3 = new Greedy();
        // int ans3 = solve3.greedy(grid);
        // System.out.println("\nMin distance: " + ans3);
    }
}