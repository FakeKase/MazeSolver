package VeeBoSolver.Solver.GA;

import java.util.Random;

public class genome 
{
    private final int[] chromosome;
    private boolean Reached;
    private int finalX, finalY, collisions, stepsTaken, distanceToGoal, cost;
    private double fitnessValue;

    
    public genome(int[][] maze)
    {
        int goalX = maze.length - 2;
        int goalY = maze[0].length - 2;
        int minSteps = Math.abs(goalX - 1) + Math.abs(goalY - 1);
        chromosome = new int[maze.length * maze[0].length];
        Random r = new Random();
        for (int i = 0; i < chromosome.length; i++) 
        {
            chromosome[i] = r.nextInt(4);
        }
        Reached = false;
        this.distanceToGoal = Integer.MAX_VALUE;
        this.finalX = this.finalY = 1;
        this.collisions = 0;
        this.stepsTaken = 0;
        this.cost = 0;
        this.fitnessValue = 0;
    }

    public genome(int length) 
    {
    chromosome = new int[length];
    }


    public int getGeneAt(int index) {return this.chromosome[index];}
    public void setGeneAt(int index, int value) {this.chromosome[index] = value;}
    public int length() {return this.chromosome.length;}
    public boolean isReachable() {return this.Reached;}
    public int getCost() {return this.cost;}
    public int getDistance() {return this.distanceToGoal;}
    public int[] getFinalAxis() {return new int[]{this.finalX, this.finalY};}
    public void setDistance(int dist) {this.distanceToGoal = dist;}
    public void setCost(int cost) {this.cost = cost;}
    public void SetFinalAxis(int x, int y) {this.finalX = x; this.finalY = y;}
    public void Collided() {this.collisions++;}
    public void walked() {this.stepsTaken++;}
    public void SetIsReached() {this.Reached = true;}
    public double getFitnessValue() {return this.fitnessValue;}
    public void setFitnessValue() {this.fitnessValue = this.fitness();}

    private double fitness()
    {
        double fitnessValue = 0;

        // Reward closeness to goal
        double distanceScore = 1000 / (distanceToGoal + 1);
        double costScore = 5000 / (cost + 1);
        fitnessValue += distanceScore + costScore;

        //punish
        fitnessValue -= collisions * 5;
        fitnessValue -= stepsTaken * 0.5;

        if (this.Reached) 
        {
            fitnessValue += 10000;
            fitnessValue += 8000.0 / (cost + 1);
            double shortnessBonus = (chromosome.length - stepsTaken) * 10;
            fitnessValue += shortnessBonus;
        }

        return fitnessValue;
    }

    public void resetState() 
    {
        this.finalX = 1;
        this.finalY = 1;
        this.stepsTaken = 0;
        this.collisions = 0;
        this.distanceToGoal = Integer.MAX_VALUE;
        this.Reached = false;
        this.cost = 0;
        this.fitnessValue = 0;
    }

    public genome copy() 
    {
        genome g = new genome(this.chromosome.length);
        System.arraycopy(this.chromosome, 0, g.chromosome, 0, chromosome.length);
        g.Reached = this.Reached;
        g.finalX = this.finalX;
        g.finalY = this.finalY;
        g.collisions = this.collisions;
        g.stepsTaken = this.stepsTaken;
        g.distanceToGoal = this.distanceToGoal;
        g.cost = this.cost;
        g.fitnessValue = this.fitnessValue;

        return g;
    }


}
