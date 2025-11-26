package VeeBoSolver.DataStructure;
import java.util.*;

public class Node {
    private final Long NodeID;
    private final ArrayList<Edge> adjL;
    private Long g;
    private Long h;
    private Long f;
    private Node parent;

    public Node(Long id)
    {
        this.NodeID = id;
        this.adjL = new ArrayList<>();
        this.g = Long.MAX_VALUE;
        this.f = 0L;
        this.h = 0L;
        this.parent = null;
    }

    public long getID() {return this.NodeID;}
    public ArrayList<Edge> getAdjL() {return this.adjL;}
    public void addEdge(Edge path) {this.adjL.add(path);}

    public void setG(Long value) {this.g = value;}
    public void setF(Long value) {this.f = value;}
    public void setH(Long value) {this.h = value;}
    public long getH() {return this.h;}
    public long getG() {return this.g;}
    public long getF() {return this.f;}

    public Node getParent() {return this.parent;}
    public void setParent(Node p) {this.parent = p;}
}
