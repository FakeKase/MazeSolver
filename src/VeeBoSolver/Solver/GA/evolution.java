package VeeBoSolver.Solver.GA;
import VeeBoSolver.DataStructure.*;
import VeeBoSolver.Solver.ansPrinter;
import java.util.*;

public class evolution 
{
    public static final int POP_SIZE = 200;
    public static final int MAX_GEN = 1000;
    public static final double MUTATION_RATE = 0.02;
    private final Random r = new Random();



    public void solve(int[][] maze)
    {
        List<genome> population = new ArrayList<>();
        for(int i = 0; i < POP_SIZE ; i++)
        {
            population.add(new genome(maze));
        }

        evaluator e = new evaluator();

        for(genome g : population)
        {
            e.evaluate(g, maze);
            g.setFitnessValue();
        }
        genome globalBest = null;

        long start = System.currentTimeMillis();
        long limit = 30000;

        for(int gen = 0 ; gen < MAX_GEN ; gen++) //run by number of gens
        //while(System.currentTimeMillis() - start < limit) //run by time
        {
            List<genome> nextGen = new ArrayList<>();
            genome best = getElite(population);
            if(globalBest == null || best.getFitnessValue() > globalBest.getFitnessValue()) 
            {
                globalBest = best.copy();
            }
            printPath(best, maze);
            genome elite = globalBest.copy();
            nextGen.add(elite);
            while(nextGen.size() < POP_SIZE)
            {
                genome ParentA = pickParent(population);
                genome ParentB = pickParent(population);

                genome offspring = crossover(ParentA, ParentB);
                mutate(offspring);

                e.evaluate(offspring, maze);
                offspring.setFitnessValue();
                nextGen.add(offspring);
            }

            population = nextGen;
           
            
        }
        printPath(globalBest, maze);
        if(globalBest.isReachable()) 
        {
            System.out.println("Minimum cost: " + globalBest.getCost());
        }
        else System.out.println("Minimum cost(Not found): " + globalBest.getCost());

    }

    private genome getElite(List<genome> population)
    {
        genome best = population.get(0);
        for(genome g : population)
        {
            if(g.getFitnessValue() > best.getFitnessValue()) best = g;
        }
        return best;
    }

    private genome pickParent(List<genome> population)
    {
        int a = r.nextInt(POP_SIZE);
        int b = r.nextInt(POP_SIZE);
        genome A = population.get(a);
        genome B = population.get(b);
        return (A.getFitnessValue() > B.getFitnessValue())? A : B;
    }

    private genome crossover(genome pA, genome pB)
    {
        genome offspring = new genome(pA.length());
        int[] crossoverpoint = new int[2];
        for(int i = 0 ; i < crossoverpoint.length ; i++)
        {
            crossoverpoint[i] = r.nextInt(pA.length());
        }

        Arrays.sort(crossoverpoint);

        for(int i = 0 ; i < pA.length() ; i++)
        {
            if(i < crossoverpoint[0]) offspring.setGeneAt(i, pA.getGeneAt(i));
            else if(i < crossoverpoint[1]) offspring.setGeneAt(i, pB.getGeneAt(i));
            else offspring.setGeneAt(i, pA.getGeneAt(i));
        }

        return offspring;
    }

    private void mutate(genome g)
    {
        for(int i = 0; i < g.length(); i++)
        {
            if(r.nextDouble() < MUTATION_RATE)
            {
                g.setGeneAt(i, r.nextInt(4));
            }
        }
    }

    private void printPath(genome g, int[][] maze)
    {
        evaluator e = new evaluator();
        ArrayList<Node> path = new ArrayList<>();
        path.add(new Node(1, 1, 0)); //start
        Node curr = path.get(0);
        for(int i = 0 ; i < g.length() ; i++)
        {
            int[] dir = e.decode(g.getGeneAt(i));
            int nx = curr.getX() + dir[0];
            int ny = curr.getY() + dir[1];
            boolean outOfBounds = nx < 0 || nx >= maze.length || ny < 0 || ny >= maze[0].length;
            if(outOfBounds || maze[nx][ny] == -1) continue;
            Node next = new Node(nx, ny, 0);
            path.add(next);
            curr = next;
            if (nx == maze.length - 2 && ny == maze[0].length - 2) break; //goal reached
        }
        ansPrinter ap = new ansPrinter();
        ap.clearScreen();
        ap.printans(maze, path);
        try { Thread.sleep(50); } catch (InterruptedException ex) {}
    }

}
