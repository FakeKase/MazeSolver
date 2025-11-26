package VeeBoSolver;
import VeeBoSolver.*;
import VeeBoSolver.DataStructure.*;
import VeeBoSolver.filereader.*;
import VeeBoSolver.Solver.*;

import java.util.*;

class Main
{
    public static void main(String[] args)
    {
        MazeData md = MazeGraphReader.readMazeAsGraph("/Users/kase/Documents/CPE231_Maze/MazeFiles/m100_90.txt");
        Graph g = md.graph;
        System.out.println("StartID = " + g.getStartID());
        System.out.println("GoalID = " + g.getGoalID());
        g.PrintMaze(md);

        Astar solver = new Astar();
        Long ans = solver.solve(g, g.getStartID(), g.getGoalID());
        System.out.println("\nMin distance: "+ ans);
        g.PrintPath(md, g.getPath());

    }
}