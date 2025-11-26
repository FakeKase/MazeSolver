package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.math.*;
import java.util.*;

public class Astar {

    public long solve(Graph g, Long StartID, Long GoalID)
    {
        if(Objects.equals(StartID, GoalID)) return 0L;

        Node start = g.getNode(StartID);
        start.setG(0L);
        start.setH(heuristic(start, g.getNode(GoalID)));
        start.setF(start.getH() + start.getG());

        PriorityQueue<Node> openList = new PriorityQueue<>((a, b) -> Long.compare(a.getF(), b.getF()));
        HashSet<Node> closedList = new HashSet<>();

        openList.add(start);
        while(!openList.isEmpty())
        {
            Node curr = openList.poll();
            if(curr.getID() == GoalID) break;
            closedList.add(curr);
            
            for(Edge i : curr.getAdjL())
            {
                if(closedList.contains(i.getDest())) continue;

                Long tentative_g = curr.getG() + i.getWeight();

                if(tentative_g < i.getDest().getG())
                {
                    i.getDest().setParent(curr);
                    i.getDest().setG(tentative_g);
                    i.getDest().setH(heuristic(i.getDest(), g.getNode(GoalID)));
                    i.getDest().setF(i.getDest().getG() + i.getDest().getH());

                    if(!openList.contains(i.getDest()))
                    {
                        openList.add(i.getDest());
                    }
                }
            }
        }

        //reconstruct path
        ArrayList<Node> Path = new ArrayList<>();
        Node curr = g.getNode(GoalID);
        while(curr != null)
        {
            Path.add(curr);
            curr = curr.getParent();
        }
        Collections.reverse(Path);
        g.setPath(Path);
        return g.getPath().get(g.getPath().size() - 1).getG() - 1;

    }

    private Long heuristic(Node curr, Node dist)
    {

        Long r1 = curr.getID() / 10000;
        Long r2 = curr.getID() % 10000;
        Long c1 = dist.getID() / 10000;
        Long c2 = dist.getID() % 10000;

        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
