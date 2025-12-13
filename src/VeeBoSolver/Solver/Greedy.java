package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Greedy {
    
    public int greedy(int[][] grid){
        Set<Node> walking = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        int R = grid.length;
        int C = grid[0].length;
        int goalX = R - 2;
        int goalY = C - 2;

        Node start = new Node(1, 1, 0);
        Node goal = null;
        walking.add(start);
        stack.add(start);
        while(!stack.isEmpty()){
            Node currPos = stack.peek();
            //reach goal
            if(currPos.getX() == goalX && currPos.getY() == goalY){
                goal = currPos;
                break;
            }
            //walk to minimum weight neighbors of current node
            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            Node v = null;
            int min = Integer.MAX_VALUE;

            for(int[] dir:directions){
                int nx = currPos.getX() + dir[0];
                int ny = currPos.getY() + dir[1];

                boolean outofgrid = nx<0 || nx>=R || ny<0 || ny>=C;
                if(outofgrid) continue;
                if(grid[nx][ny] == -1) continue;
        
                Node next = new Node(nx, ny, currPos.getCost()+grid[nx][ny]);
                if(!walking.contains(next) && grid[nx][ny] <= min){
                    v = next;
                    min = grid[nx][ny];
                }
            }
            //check for backtrack
            if(v!=null){ walking.add(v); stack.push(v); v.setParent(currPos); }
            else { stack.pop(); }
        }

        int cost = goal.getCost();
        //reconstruct path
        ArrayList<Node> Path = new ArrayList<>();
        while(goal != null)
        {
            Path.add(goal);
            goal = goal.getParent();
        }
        Collections.reverse(Path);
        ansPrinter ap = new ansPrinter();
        ap.printanstemp(grid, Path);
        return cost;
    }
}