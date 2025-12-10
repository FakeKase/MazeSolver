package VeeBoSolver.DataStructure;

import java.util.Objects;

public class MatrixNode 
{
    public int x,y,g;
    public MatrixNode parent;

    public MatrixNode(int r, int c, int g)
    {
        this.g = g;
        this.x = r;
        this.y = c;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (!(o instanceof MatrixNode)) return false;
        MatrixNode a = (MatrixNode) o;
        return this.x == a.x && this.y == a.y;
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(x, y);
    }
}
