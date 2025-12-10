package VeeBoSolver.DataStructure;

import java.util.Objects;

public class Node 
{
    private int x,y,g;
    private Node parent;

    public Node(int r, int c, int g)
    {
        this.g = g;
        this.x = r;
        this.y = c;
    }


    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node a = (Node) o;
        return this.x == a.x && this.y == a.y;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(x, y);
    }

    public int getX() { return this.x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return this.y; }
    public void setY(int y) { this.y = y; }
    public int getCost() { return this.g; }
    public void setCost(int g) { this.g = g; }
    public Node getParent() { return this.parent; }
    public void setParent(Node parent) { this.parent = parent; }
}
