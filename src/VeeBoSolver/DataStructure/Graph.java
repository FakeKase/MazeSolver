package VeeBoSolver.DataStructure;
import VeeBoSolver.filereader.*;
import java.util.*;

import VeeBoSolver.filereader.MazeData;



public class Graph {
    private final Map<Long, Node> graph = new HashMap<>(); //Search by node's Id
    private long startID;
    private long goalID;
    private ArrayList<Node> shortestPath;

    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    
    public void setStartAndGoal(long startID, long goalID) 
    {
        this.startID = startID;
        this.goalID = goalID;
    }

    public Node addNode(Long id)
    {
        Node n = new Node(id);
        graph.put(id, n);
        return n;
    }

    public Node getNode(Long id)
    {
        return this.graph.get(id);
    }

    public Collection<Node> getAllNodes()
    {
        return graph.values();
    }

    public long getStartID() { return startID; }
    public long getGoalID() { return goalID; }

    public ArrayList<Node> getPath() {return this.shortestPath;}
    public void setPath(ArrayList<Node> temp) {this.shortestPath = temp;}

    public void PrintMaze(MazeData mazeData) {
        // Step 1: Build a 2D string array to represent the maze
        String[][] display = new String[mazeData.rows][mazeData.cols];
        
        // Step 2: Fill all cells as walls by default
        for (int r = 0; r < mazeData.rows; r++) {
            Arrays.fill(display[r], "#");
        }
    
        // Step 3: Fill walkable cells with weights or start/goal
        for (int r = 0; r < mazeData.rows; r++) {
            for (int c = 0; c < mazeData.cols; c++) {
                Node n = mazeData.nodeGrid[r][c];
                if (n != null) {
                    if (n.getID() == startID) display[r][c] = "S"; // pad for alignment
                    else if (n.getID() == goalID) display[r][c] = " G";
                    else {
                        String val = mazeData.cellValues.get(r).get(c);
                        val = val.replaceAll("\"", ""); // remove quotes
                    
                        // Pad single digit numbers with space for alignment
                        if (val.length() == 1) val = "" + val;
                        display[r][c] = val;
                    }
                }
            }
        }
    
        // Step 4: Print maze row by row
        for (int r = 0; r < mazeData.rows; r++) {
            for (int c = 0; c < mazeData.cols; c++) {
                System.out.print(display[r][c] + " "); // add space between cells
            }
            System.out.println();
        }
    }

    public void PrintPath(MazeData mazeData, ArrayList<Node> path) {
        ArrayList<Node> pathtemp = new ArrayList<>(path);
        String[][] display = new String[mazeData.rows][mazeData.cols];
        
        // Step 2: Fill all cells as walls by default
        for (int r = 0; r < mazeData.rows; r++) {
            Arrays.fill(display[r], RESET + "#");
        }
    
        // Step 3: Fill walkable cells with weights or start/goal
        for (int r = 0; r < mazeData.rows; r++) {
            for (int c = 0; c < mazeData.cols; c++) {
                Node n = mazeData.nodeGrid[r][c];
                if (n != null) {
                    if (n.getID() == startID) display[r][c] = GREEN + "S"; // pad for alignment
                    else if (n.getID() == goalID) display[r][c] = GREEN + "G";
                    else if (path.contains(n))
                    {
                        String val = mazeData.cellValues.get(r).get(c);
                        val = val.replaceAll("\"", ""); // remove quotes
                    
                        // Pad single digit numbers with space for alignment
                        if (val.length() == 1) val = "" + val;
                        display[r][c] = GREEN + val;
                    }
                    else {
                        String val = mazeData.cellValues.get(r).get(c);
                        val = val.replaceAll("\"", ""); // remove quotes
                    
                        // Pad single digit numbers with space for alignment
                        if (val.length() == 1) val = "" + val;
                        display[r][c] = RESET + " ";
                    }
                }
            }
        }
    
        // Step 4: Print maze row by row
        for (int r = 0; r < mazeData.rows; r++) {
            for (int c = 0; c < mazeData.cols; c++) {
                System.out.print(display[r][c] + " "); // add space between cells
            }
            System.out.println();
        }
    }

}