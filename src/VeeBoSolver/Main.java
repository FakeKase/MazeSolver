package VeeBoSolver;
import VeeBoSolver.DataStructure.*;
import VeeBoSolver.Solver.*;
import VeeBoSolver.filereader.*;

class Main
{
    public static void main(String[] args)
    {
        MazeData md = MazeGraphReader.readMazeAsGraph("./MazeFiles/m15_15.txt");
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