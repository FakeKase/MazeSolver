package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
import java.util.*;

public class Astar {

    public int solve(int[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        int goalX = R - 2;
        int goalY = C - 2;
        int[][] gCost = new int[R][C];
        for(int[] i : gCost)
            {
                Arrays.fill(i, Integer.MAX_VALUE);
            }
        gCost[1][1] = 0;
            

        PriorityQueue<Node> openSet = new PriorityQueue<>((a,b) -> 
        {
            int f1 = a.getCost() + heuristic(a, goalX, goalY);
            int f2 = b.getCost() + heuristic(b, goalX, goalY);
            return f1 - f2;
        });

        Node start = new Node(1, 1, 0);
        Node goal = null;
        openSet.add(start);

        while(!openSet.isEmpty())
        {
            Node curr = openSet.poll();

            if(curr.getX() == goalX && curr.getY() == goalY) 
            {
                goal = curr;
                break;
            }

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

            for(int[] dir : directions)
            {
                int nx = curr.getX() + dir[0];
                int ny = curr.getY() + dir[1];

                boolean outofgrid = nx < 0 || nx >= R || ny < 0 || ny >= C;

                if(outofgrid) continue;

                if(grid[nx][ny] == -1) continue;

                int tentativeG = curr.getCost() + grid[nx][ny];

                if(tentativeG >= gCost[nx][ny]) continue;

                gCost[nx][ny] = tentativeG;
                Node next = new Node(nx, ny, tentativeG);
                next.setParent(curr);
                openSet.add(next);
            }
        }

        ArrayList<Node> Path = new ArrayList<>();
        while(goal != null)
        {
            Path.add(goal);
            goal = goal.getParent();
        }
        Collections.reverse(Path);
        ansPrinter ap = new ansPrinter();
        ap.printanstemp(grid, Path);
        return gCost[goalX][goalY];

    }

    private int heuristic(Node a, int goalX, int goalY)
    {
        return Math.abs(goalX - a.getX()) + Math.abs(goalY - a.getY());
    }
}
