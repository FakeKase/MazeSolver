package VeeBoSolver;
import VeeBoSolver.Solver.*;
import VeeBoSolver.Solver.GA.*;
import VeeBoSolver.filereader.*;
import java.util.*;

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        OUTER:
        while (true) {
            System.out.println("-------Select Algorithm to solve maze-------\n1. A* Algorithm\n2. Dijkstra Algorithm\n3. Greedy DFS Algorithm\n4. Genetic Algorithm");
            System.out.print("Your choice(enter -1 to exit): ");
            choice = sc.nextInt();
            if(choice == -1 || choice > 4) break;
            System.out.print("Enter rows and columns: ");
            String row = sc.next();
            String column = sc.next();
            int[][] grid = Reader.buildMatrix("./MazeFiles/m"+row+"_"+column+".txt");
            for(int[] i : grid)
            {
                System.out.println(Arrays.toString(i));
            }
            long start = Long.MAX_VALUE;
            switch (choice) {
                case 1 -> {
                    Astar solver = new Astar();
                    start = System.nanoTime();
                    int ans = solver.solve(grid);
                    System.out.println("Min distance: " + ans);
                }
                case 2 -> {
                    Dijkstra solver2 = new Dijkstra();
                    start = System.nanoTime();
                    int ans2 = solver2.dijkstra(grid);
                    System.out.println("Min distance: " + ans2);
                }
                case 3 -> {
                    Greedy solve3 = new Greedy();
                    start = System.nanoTime();
                    int ans3 = solve3.greedy(grid);
                    System.out.println("\nMin distance: " + ans3);
                }
                case 4 -> {
                    evolution ev = new evolution(200, 1500, 0.1);
                    System.out.println("Population sizes: " + ev.getPop() + "\nNumber of Generations: " + ev.getGen() + "\nMutation Rate: " + ev.getMutation());
                    System.out.print("Do you want to edit parameter? (yes/no): ");
                    String temp = sc.next();
                    if (temp.toLowerCase().equals("yes")) {
                        System.out.println();
                        System.out.println("What parameter you want to edit?\n1. Population sizes\n2. Number of Generations\n3. Mutation Rate");
                        int c = sc.nextInt();
                        if (c == -1 || c > 3) {
                            break OUTER;
                        }
                        System.out.println();
                        switch (c) {
                            case 1 -> {
                                System.out.print("Enter new Population sizes: ");
                                int pop = sc.nextInt();
                                ev = new evolution(pop, 1500, 0.1);
                            }
                            case 2 -> {
                                System.out.print("Enter new Number of Generations: ");
                                int gen = sc.nextInt();
                                ev = new evolution(200, gen, 0.1);
                            }
                            case 3 -> {
                                System.out.print("Enter new Mutation Rate: ");
                                int mu = sc.nextInt();
                                ev = new evolution(200, 1500, mu);
                            }
                            default -> {
                            }
                        }
                    }
                    start = System.nanoTime();
                    ev.solve(grid);
                }
                default -> {
                }
            }
            long end = System.nanoTime();
            long temp = end - start;
            double time = (double) temp / 1_000_000_000.0;
            System.out.println("Run time: " + time + " s");
        }
        sc.close();
    }
}