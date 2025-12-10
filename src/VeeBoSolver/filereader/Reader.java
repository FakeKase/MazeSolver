package VeeBoSolver.filereader;

import VeeBoSolver.DataStructure.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Reader {

    public static MazeData readMazeAsGraph(String filePath) {

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

        Graph graph = new Graph();
        Node[][] nodeGrid = new Node[R][C];

        long startID = -1;
        long goalID = -1;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                String cell = rows.get(r).get(c);
                if (!cell.equals("#")) {
                    long id = r * 10000 + c;
                    nodeGrid[r][c] = graph.addNode(id);
                    if (cell.equals("S")) startID = id;
                    else if (cell.equals("G")) goalID = id;
                }
            }
        }

        graph.setStartAndGoal(startID, goalID);

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (nodeGrid[r][c] == null) continue;
                Node curr = nodeGrid[r][c];
                for (int[] d : directions) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (nodeGrid[nr][nc] == null) continue;
                    Node neighbor = nodeGrid[nr][nc];
                    int weight = parseWeight(rows.get(nr).get(nc));
                    curr.addEdge(new Edge(curr, neighbor, weight));
                }
            }
        }

        return new MazeData(graph, nodeGrid, rows, R, C);
    }

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

    private static int parseWeight(String token) {
        if (token.equals("S") || token.equals("G")) return 1;
        if (token.startsWith("\"") && token.endsWith("\"")) {
            return Integer.parseInt(token.substring(1, token.length()-1));
        }
        try {
            return Integer.parseInt(token);
        } catch (Exception e) {
            return 1;
        }
    }
}