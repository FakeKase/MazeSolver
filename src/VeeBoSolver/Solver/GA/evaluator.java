package VeeBoSolver.Solver.GA;

public class evaluator 
{
    public void evaluate(genome g, int[][] maze)
    {
        g.resetState();
        int goalX = maze.length - 2;
        int goalY = maze[0].length - 2;
        int[] pos = new int[]{1,1};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[1][1] = true;
        int stuckCount = 0;
        int lastX = pos[0], lastY = pos[1];
        for(int i = 0 ; i < g.length() ; i++)
        {
            int[] dir = decode(g.getGeneAt(i));
            int nx = pos[0] + dir[0];
            int ny = pos[1] + dir[1];
            boolean outOfBounds = nx < 0 || nx >= maze.length || ny < 0 || ny >= maze[0].length;
            if(outOfBounds || maze[nx][ny] == -1) {g.Collided(); continue;}
            else
            {
                g.walked();
                pos[0] = nx; pos[1] = ny;
                if (visited[pos[0]][pos[1]]) { g.Collided(); continue; }
                visited[pos[0]][pos[1]] = true;
                int currCost = g.getCost();
                currCost += maze[pos[0]][pos[1]];
                g.setCost(currCost);
            } 

            if (pos[0] == lastX && pos[1] == lastY) 
            {
                stuckCount++;
                if (stuckCount > 5) break;
            } 
            else 
            {
                stuckCount = 0;
            }
            lastX = pos[0];
            lastY = pos[1];

            if(pos[0] == goalX && pos[1] == goalY) { g.SetIsReached(); break; }

        }
        
        g.setDistance(manhattan(maze, pos));
        g.SetFinalAxis(pos[0], pos[1]);

        double fitness;

        if (g.isReachable()) fitness = 100000 - g.getCost() * 10 - g.getDistance() * 5 - g.getStep() * 2;
        else fitness = 5000 - g.getDistance() * 50 - g.getCollision() * 100 - g.getStep();   

        g.setFitnessValue(Math.max(1, fitness));
    }

    private int manhattan(int[][] maze, int[] pos)
    {
        int goalX = maze.length - 2;
        int goalY = maze[0].length - 2;
        return Math.abs(goalX - pos[0]) + Math.abs(goalY - pos[1]);
    }


    public int[] decode(int gene)
    {
        return switch (gene) 
        {
            case 0 -> new int[]{0,-1};
            case 1 -> new int[]{0,1};
            case 2 -> new int[]{1,0};
            case 3 -> new int[]{-1,0};
            default -> new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        };

    }
}
