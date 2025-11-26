package VeeBoSolver.filereader;
import VeeBoSolver.DataStructure.*;

import java.util.List;

public class MazeData {
    public Graph graph;
    public Node[][] nodeGrid;
    public List<List<String>> cellValues;
    public int rows;
    public int cols;

    public MazeData(Graph graph, Node[][] nodeGrid, List<List<String>> cellValues, int rows, int cols) {
        this.graph = graph;
        this.nodeGrid = nodeGrid;
        this.cellValues = cellValues;
        this.rows = rows;
        this.cols = cols;
    }
}
