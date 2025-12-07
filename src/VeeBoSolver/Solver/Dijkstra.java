package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Dijkstra {
    public long dijkstra(Graph g, long StartID, long GoalID){
        Set<Long> walking = new HashSet<>();
        HashMap<Long, Long> dist = new HashMap<>();
        for(Node i:g.getAllNodes())
            dist.put(i.getID(), Long.MAX_VALUE);
        dist.put(StartID, 0L);

        //priority queue ordered by distance
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> 
                                Long.compare(dist.get(a.getID()), dist.get(b.getID())));
        pq.add(g.getNode(StartID));
        while(!pq.isEmpty()){
            Node currPos = pq.poll();
            if(walking.contains(currPos.getID())) continue;
            walking.add(currPos.getID());
            if(currPos.getID() == GoalID) break;

            for(Edge e:g.getNode(currPos.getID()).getAdjL()){
                Node next = e.getDest();
                long weight = dist.get(currPos.getID()) + e.getWeight();
                if(weight < dist.get(next.getID())){
                    dist.put(next.getID(), weight);
                    next.setParent(currPos);
                    pq.add(next);
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

        return dist.get(GoalID)-1;
    }
}
