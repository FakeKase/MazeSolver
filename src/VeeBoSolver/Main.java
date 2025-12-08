package VeeBoSolver;
import VeeBoSolver.DataStructure.*;
import VeeBoSolver.Solver.*;
import VeeBoSolver.filereader.*;

class Main
{
    public static void main(String[] args)
    {
        MazeData md = MazeGraphReader.readMazeAsGraph("./MazeFiles/m24_20.txt");
        Graph g = md.graph;
        System.out.println("StartID = " + g.getStartID());
        System.out.println("GoalID = " + g.getGoalID());
        g.PrintMaze(md);

        Astar solver = new Astar();
        Long ans = solver.solve(g, g.getStartID(), g.getGoalID());
        System.out.println("\nMin distance(Astar): "+ ans);
        g.PrintPath(md, g.getPath());

        Dijkstra solveDijkstra = new Dijkstra();
        Long ansDijkstra = solveDijkstra.dijkstra(g, g.getStartID(), g.getGoalID());
        System.out.println("\nMin distance: "+ ansDijkstra);
        g.PrintPath(md, g.getPath());

        Greedy solveGreedy = new Greedy();
        Long ansGreedy = solveGreedy.greedy(g, g.getStartID(), g.getGoalID());
        System.out.println("\nMin distance(Greedy): "+ ansGreedy);
        g.PrintPath(md, g.getPath());
    }
}