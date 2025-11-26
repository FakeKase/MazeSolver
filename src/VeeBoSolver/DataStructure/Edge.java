package VeeBoSolver.DataStructure;

public class Edge {
    private final Node start;
    private final Node dest;
    private final int weight;

    public Edge(Node start, Node dest, int weight)
    {
        this.start = start;
        this.dest = dest;
        this.weight = weight;
    }

    public Node getStart() {return this.start;}
    public Node getDest() {return this.dest;}
    public int getWeight() {return this.weight;}
}
