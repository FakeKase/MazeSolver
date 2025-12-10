package VeeBoSolver.filereader;

import VeeBoSolver.DataStructure.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Reader {

    public static int[][] buildMatrix(String filepath)
    {
        String[][] gridString = readMazeAsMatrix(filepath);
        int R = gridString.length;
        int C = gridString[0].length;
        int[][] weightGrid = new int[R][C];
        for (int r = 0; r < R; r++) 
        {
            for (int c = 0; c < C; c++) 
            {
                String cell = gridString[r][c];
                switch (cell) 
                {
                case "#":
                    weightGrid[r][c] = -1;
                    break;
                case "S":
                    weightGrid[r][c] = 0;
                    break;
                case "G":
                    weightGrid[r][c] = 0;
                    break;
                default:
                    weightGrid[r][c] = Integer.parseInt(cell.replace("\"", ""));
                    break;
                }
            }
        }

        return weightGrid;
    }

    private static String[][] readMazeAsMatrix(String filePath) {

        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Pattern cellPattern = Pattern.compile("#|S|G|\"\\d+\"|\\d");
                Matcher m = cellPattern.matcher(line);
                List<String> tokens = new ArrayList<>();
                while (m.find()) tokens.add(m.group());
                rows.add(tokens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int R = rows.size();
        int C = rows.get(0).size();

       String[][] grid = new String[R][C];
       for(int r = 0; r < R; r++)
       {
            for (int c = 0; c < C; c++) 
            {
                grid[r][c] = rows.get(r).get(c);
            }
       }

        return grid;
    }
}