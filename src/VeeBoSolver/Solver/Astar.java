package VeeBoSolver.Solver;
import VeeBoSolver.DataStructure.*;
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
            

        PriorityQueue<MatrixNode> openSet = new PriorityQueue<>((a,b) -> 
        {
            int f1 = a.g + heuristic(a, goalX, goalY);
            int f2 = b.g + heuristic(b, goalX, goalY);
            return f1 - f2;
        });

        MatrixNode start = new MatrixNode(1, 1, 0);
         MatrixNode goal = null;
        openSet.add(start);

        while(!openSet.isEmpty())
        {
            MatrixNode curr = openSet.poll();

            if(curr.x == goalX && curr.y == goalY) 
            {
                goal = curr;
                break;
            }

            int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

            for(int[] dir : directions)
            {
                int nx = curr.x + dir[0];
                int ny = curr.y + dir[1];

                boolean outofgrid = nx < 0 || nx >= R || ny < 0 || ny >= C;

                if(outofgrid) continue;

                if(grid[nx][ny] == -1) continue;

                int tentativeG = curr.g + grid[nx][ny];

                if(tentativeG >= gCost[nx][ny]) continue;

                gCost[nx][ny] = tentativeG;
                MatrixNode next = new MatrixNode(nx, ny, tentativeG);
                next.parent = curr;
                openSet.add(next);
            }
        }

        ArrayList<MatrixNode> Path = new ArrayList<>();
        while(goal != null)
        {
            Path.add(goal);
            goal = goal.parent;
        }
        Collections.reverse(Path);
        ansPrinter ap = new ansPrinter();
        ap.printans(grid, Path);
        return gCost[goalX][goalY];

    }

    private int heuristic(MatrixNode a, int goalX, int goalY)
    {
        return Math.abs(goalX - a.x) + Math.abs(goalY - a.y);
    }
}
