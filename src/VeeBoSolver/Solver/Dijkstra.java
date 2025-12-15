package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Dijkstra {
    
    public int dijkstra(int[][] grid){
        Set<Node> walking = new HashSet<>();
        int R = grid.length;
        int C = grid[0].length;
        int goalX = R - 2;
        int goalY = C - 2;
        int[][] dist = new int[R][C];
        for(int[] row:dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[1][1] = 0;

        //priority queue ordered by distance
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.getCost() - b.getCost());
        Node start = new Node(1, 1, 0);
        Node goal = null;
        pq.add(start);
        while(!pq.isEmpty()){
            Node currPos = pq.poll();
            if(walking.contains(currPos)) continue;
            walking.add(currPos);
            if(currPos.getX() == goalX && currPos.getY() == goalY) {
                goal = currPos;
                break;
            }

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

            //check all neighbors
            for(int[] dir:directions){
                int nx = currPos.getX() + dir[0];
                int ny = currPos.getY() + dir[1];

                //prevent walking out of maze
                boolean outofgrid = nx<0 || nx>=R || ny<0 || ny>=C;
                if(outofgrid) continue;
                if(grid[nx][ny] == -1) continue;
                int cost = dist[currPos.getX()][currPos.getY()] + grid[nx][ny];
                if(cost >= dist[nx][ny]) continue;
                dist[nx][ny] = cost;

                Node next = new Node(nx, ny, cost);
                next.setParent(currPos);
                pq.add(next);
            }
        }

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
        return dist[goalX][goalY];
    }
}
