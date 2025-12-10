package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Greedy {
    
    public int greedy(int[][] g, int StartID, int GoalID){
        Set<Integer> walking = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        // Stack<Integer> path = new Stack<>();

        walking.add(StartID);
        stack.push(StartID);
        // path.add(StartID);
        int i=1, j=1;
        while(!stack.isEmpty()){
            int currPos = stack.peek();
            //reach goal
            if(currPos == GoalID) break;
            //walk to minimum weight neighbors of current node
            int v = -1;
            int min = Integer.MAX_VALUE;
            if(g[i+1][j] != -1 || g[i][j+1] != -1){
                if(g[i+1][j] <= min && !walking.contains(g[i+1][j])){
                    v = g[i+1][j];
                    min = g[i+1][j];
                }
                if(g[i][j+1] <= min && !walking.contains(g[i][j+1])){
                    v = g[i][j+1];
                    min = g[i][j+1];
                }
            }
            
            // for(Edge currEdge:g.getNode(currPos.getID()).getAdjL()){
            //     if(!walking.contains(currEdge.getDest().getID()) && currEdge.getWeight()<=min){
            //         v = currEdge.getDest();
            //         min = currEdge.getWeight();
            //     }   
            // }
            
            //check for backtrack
            if(v!=-1){ walking.add(v); stack.push(v); }
            else { stack.pop(); }
        }

        //reconstruct path
        ArrayList<Integer> Path = new ArrayList<>();
        int curr = GoalID;
        while(curr != -1 && !stack.isEmpty())
        {
            Path.add(curr);
            curr = stack.pop();
        }
        Collections.reverse(Path);
        
        return sumWeight(Path);
    }

    private int sumWeight(ArrayList<Integer> path){
        int weight = 0;
        for(int i:path){
            weight += i;
        }
        return weight-1;
    }
}
