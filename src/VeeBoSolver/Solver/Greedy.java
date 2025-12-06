package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Greedy {
    public long greedy(Graph g, long StartID, long GoalID){
        Set<Long> walking = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        walking.add(StartID);
        stack.add(g.getNode(StartID));
        while(!stack.isEmpty()){
            Node currPos = stack.peek();
            //reach goal
            if(currPos.getID() == GoalID) break;
            //walk to minimum weight neighbors of current node
            Node v = null;
            int min = Integer.MAX_VALUE;
            for(Edge currEdge:g.getNode(currPos.getID()).getAdjL()){
                if(!walking.contains(currEdge.getDest().getID()) && currEdge.getWeight()<=min){
                    v = currEdge.getDest();
                    min = currEdge.getWeight();
                }   
            }
            //check for backtrack
            if(v!=null){ walking.add(v.getID()); stack.push(v); v.setParent(currPos); }
            else { stack.pop(); }
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
        return sumWeight(Path);
    }

    private long sumWeight(ArrayList<Node> path){
        int weight = 0;
        for(int i=0; i<path.size()-1; i++){
            Node pcurr = path.get(i);
            Node pnext = path.get(i+1);
            for(Edge e:pcurr.getAdjL()){
                if(e.getDest().getID() == pnext.getID()) { weight+=e.getWeight(); break; }
            }
        }
        return weight-1;
    }
}
