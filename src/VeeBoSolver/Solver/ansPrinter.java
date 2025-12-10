package VeeBoSolver.Solver;

import VeeBoSolver.DataStructure.MatrixNode;

import java.util.*;

public class ansPrinter 
{
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public void printans(int[][] grid, ArrayList<MatrixNode> path)
    {
        ArrayList<MatrixNode> pathtemp = new ArrayList<>(path);
        int R = grid.length;
        int C = grid[0].length;
        String[][] display = new String[R][C];
        for (int r = 0; r < R; r++) {
            Arrays.fill(display[r], RESET + "#");
        }

        for (int r = 0; r < R; r++) 
        {
            for (int c = 0; c < C; c++) 
            {
                MatrixNode n = new MatrixNode(r, c, 0);
                if (n.x == 1 && n.y == 1) display[r][c] = GREEN + "S";
                else if (n.x == R - 2  && n.y == C - 2) display[r][c] = GREEN + "G";
                else if(grid[r][c] == -1)
                {
                    String val = "#";
                    val = val.replaceAll("\"", "");
                    if (val.length() == 1) val = "" + val;
                    display[r][c] = RESET + val;
                }
                else if (path.contains(n))
                {
                    String val = String.valueOf(grid[r][c]);
                    val = val.replaceAll("\"", "");
                    if (val.length() == 1) val = "" + val;
                    display[r][c] = GREEN + val;
                }
                else 
                {
                    String val = String.valueOf(grid[r][c]);
                    val = val.replaceAll("\"", "");
                    if (val.length() == 1) val = "" + val;
                    display[r][c] = RESET + val;
                }
            }        
        }

        for (int r = 0; r < R; r++) 
        {
            for (int c = 0; c < C; c++) 
            {
                System.out.print(display[r][c] + " ");
            }
            System.out.println();
        }
    }

}
