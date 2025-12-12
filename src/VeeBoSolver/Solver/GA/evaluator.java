package VeeBoSolver.Solver.GA;

public class evaluator 
{
    public void evaluate(genome g, int[][] maze)
    {
        g.resetState();
        int goalX = maze.length - 2;
        int goalY = maze[0].length - 2;
        int[] pos = new int[]{1,1};
        for(int i = 0 ; i < g.length() ; i++)
        {
            int[] dir = decode(g.getGeneAt(i));
            int nx = pos[0] + dir[0];
            int ny = pos[1] + dir[1];
            boolean outOfBounds = nx < 0 || nx >= maze.length || ny < 0 || ny >= maze[0].length;
            if(outOfBounds || maze[nx][ny] == -1) g.Collided();
            else
            {
                g.walked();
                pos[0] = nx; pos[1] = ny;
                int currCost = g.getCost();
                currCost += maze[pos[0]][pos[1]];
                g.setCost(currCost);
            } 

            if(pos[0] == goalX && pos[1] == goalY) { g.SetIsReached(); break; }

        }
        
        g.setDistance(manhattan(maze, pos));
        g.SetFinalAxis(pos[0], pos[1]);
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
