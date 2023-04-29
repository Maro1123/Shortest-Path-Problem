package CLI;

import Graph.Graph;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class CLI {
    State state;
    Graph currentGraph;
    int singleSource;
    int[] parents;
    double[] costs;
    int[][] pairParents;
    double[][] pairCosts;
    boolean negativeCycles;
    Scanner sc;

    public CLI(){
        sc = new Scanner(System.in);
    }

    void initialize(){
        System.out.println(" __          __  _                          ");
        System.out.println(" \\ \\        / / | |                         ");
        System.out.println("  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___ ");
        System.out.println("   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("    \\  /\\  /  __/ | (_| (_) | | | | | |  __/");
        System.out.println("     \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
        System.out.println("\nPlease Enter the name of the file that contains the graph data:");
        while(true){
            try{
                currentGraph = new Graph(sc.nextLine());
                break;
            }catch (FileNotFoundException e){
                System.out.println("File not found, please try again.");
            }
        }
    }

    // Used to reset costs and parent arrays
    // Sets state to INIT
    public void setArrays(){
        state = State.INIT;
        costs = new double[currentGraph.getSize()];
        parents = new int[currentGraph.getSize()];
        pairCosts = new double[currentGraph.getSize()][currentGraph.getSize()];
        pairParents = new int[currentGraph.getSize()][currentGraph.getSize()];
        negativeCycles = false;
    }

    public State getState(){return state;}

    // Chooses operation to run from single source paths, all pair paths, test for negative cycles, or exit.
    // Changes state according to user choice to enter sub-menus.
    public void mainMenu(){
        int choice;
        while(true){
            System.out.println("  __  __       _         __  __                  ");
            System.out.println(" |  \\/  |     (_)       |  \\/  |                 ");
            System.out.println(" | \\  / | __ _ _ _ __   | \\  / | ___ _ __  _   _ ");
            System.out.println(" | |\\/| |/ _` | | '_ \\  | |\\/| |/ _ \\ '_ \\| | | |");
            System.out.println(" | |  | | (_| | | | | | | |  | |  __/ | | | |_| |");
            System.out.println(" |_|  |_|\\__,_|_|_| |_| |_|  |_|\\___|_| |_|\\__,_|");
            System.out.println("""
                    
                    Choose the operation you want to run:
                     1. Shortest path from a single node\s
                     2. Shortest path between all nodes\s
                     3. Check for negative cycles\s
                     4. Exit""");
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    getShortestPaths();
                    return;
                } else if (choice == 2) {
                    getAllShortestPaths();
                    return;
                } else if (choice == 3) {
                    checkNegativeCycles();
                    return;
                } else if (choice == 4){
                    System.out.println("  ____              ");
                    System.out.println(" |  _ \\                 _____");
                    System.out.println(" | |_) |_   _  ___     /     \\");
                    System.out.println(" |  _ <| | | |/ _ \\   |  ^  ^ |");
                    System.out.println(" | |_) | |_| |  __/   |  \\__/ |");
                    System.out.println(" |____/ \\__, |\\___|    \\_____/");
                    System.out.println("         __/ |      ");
                    System.out.println("        |___/       ");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input! try again.");
                }
            }catch(Exception e){
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    // Runs algorithms for all shortest paths from a single source
    // Changes state to SSSP or FSSSP
    public void getShortestPaths(){
        int choice;
        System.out.print("Please enter the source node: ");
        singleSource = sc.nextInt();
        System.out.println("""
                
                Choose the method you would like to run to find all shortest paths from this node:
                 1. Dijkstra\s
                 2. Bellman-Ford\s
                 3. Floyd-Warshall""");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {
                    if (currentGraph.hasNegativeWeights()){
                        System.out.println("""
                                
                                The input graph has negative edge weights which Dijkstra's algorithm cannot operate on.
                                Please try using another algorithm.""");
                    }else {
                        currentGraph.dijkstra(singleSource, costs, parents);
                        state = State.SSSP;
                        return;
                    }
                }else if (choice == 2) {
                    negativeCycles = currentGraph.bellmanFord(singleSource, costs, parents);
                    state = State.SSSP;
                    return;
                }else if (choice == 3) {
                    negativeCycles = currentGraph.floydWarshall(pairCosts, pairParents);
                    state = State.FSSSP;
                    return;
                }else {
                    System.out.println("Invalid input! try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
    }

    // Runs algorithms for finding all shortest paths between all pairs of nodes
    // Changes state to APSP
    public void getAllShortestPaths(){
        int choice;
        System.out.println("""
                
                Choose the method you would like to run to find all shortest paths between all pairs of nodes:
                 1. Dijkstra\s
                 2. Bellman-Ford\s
                 3. Floyd-Warshall""");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {
                    if (currentGraph.hasNegativeWeights()){
                        System.out.println("""
                                
                                The input graph has negative edge weights which Dijkstra's algorithm cannot operate on.
                                Please try using another algorithm or using a different graph.""");
                    }else{
                        for (int i = 0; i < currentGraph.getSize(); i++)
                            currentGraph.dijkstra(i, pairCosts[i], pairParents[i]);
                        break;
                    }
                }
                else if (choice == 2) {
                    for (int i=0 ; i<currentGraph.getSize() ; i++)
                        negativeCycles = currentGraph.bellmanFord(i, pairCosts[i], pairParents[i]);
                    break;
                }
                else if (choice == 3) {
                    negativeCycles = currentGraph.floydWarshall(pairCosts, pairParents);
                    break;
                }
                else {
                    System.out.println("Invalid input! try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
        state = State.APSP;
    }

    // Checks for negative cycles
    // DOES NOT change state from INIT
    public void checkNegativeCycles(){
        int choice;
        System.out.println("""
                
                Choose the method you would like to run to check if there are negative cycles:
                 1. Bellman-Ford\s
                 2. Floyd-Warshall""");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {negativeCycles = currentGraph.bellmanFord(0, costs, parents); break;}
                else if (choice == 2) {negativeCycles = currentGraph.floydWarshall(pairCosts, pairParents); break;}
                else {System.out.println("Invalid input! try again.");}
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
        if(!negativeCycles) System.out.println("The graph does not have negative cycles.");
        else System.out.println("The graph has negative cycles.");
        setArrays();
    }

    // -------------------------------------------------- USER POLLS --------------------------------------------------

    // Get distance from a predefined source to a destination
    public void sourceDistance(int dest) throws Exception{
        if (negativeCycles) {
            System.out.println("""
                    
                    Algorithm yielded incorrect results because graph contains negative cycles.
                    Restart the application with a new graph if you wish to run the algorithm.""");
            return;
        }
        if (dest<0 || dest>=currentGraph.getSize()) throw new Exception();
        double[] c = (state == State.SSSP)? costs : pairCosts[singleSource];
        if(c[dest] == Integer.MAX_VALUE)
            System.out.println("There is no path from node " + singleSource + " to node " + dest);
        else
            System.out.println("Distance from node " + singleSource + " to node " + dest + " = " + c[dest]);
    }

    // Get distance from any source to any destination
    public void pairDistance(int source, int dest) throws Exception{
        if (negativeCycles) {
            System.out.println("""
                    
                    Algorithm yielded incorrect results because graph contains negative cycles.
                    Restart the application with a new graph if you wish to run the algorithm.""");
            return;
        }
        if(dest<0 || dest>=currentGraph.getSize()) throw new Exception();
        if(source<0 || source>=currentGraph.getSize()) throw new Exception();
        if(pairCosts[source][dest] == Integer.MAX_VALUE)
            System.out.println("There is no path from node " + source + " to node " + dest);
        else
            System.out.println("Distance from node " + source + " to node " + dest + " = " + pairCosts[source][dest]);
    }

    // Get path from a predefined source to a destination
    public void sourcePath(int dest) throws Exception{
        if (negativeCycles) {
            System.out.println("""
                    
                    Algorithm yielded incorrect results because graph contains negative cycles.
                    Restart the application with a new graph if you wish to run the algorithm.""");
            return;
        }
        if(dest<0 || dest>=currentGraph.getSize()) throw new Exception();
        int[] p = (state == State.SSSP)? parents : pairParents[singleSource];
        Stack<Integer> path = new Stack<>();
        int current = dest;
        if(p[current] == -1 && current != singleSource) {   //check for path non-existence
            System.out.println("There is no path from node " + singleSource + " to node " + dest);
            return;
        }
        //Push the path in reverse order in the stack
        do{
            path.push(current);
            current = p[current];
        }while(current != singleSource);
        path.push(singleSource);
        //Pop the path back in correct order
        System.out.print("Shortest path from node " + singleSource + " to node " + dest + ": ");
        while(!path.empty()) System.out.print(path.pop() + (path.size() > 0 ? " -> " : ""));
        System.out.println();
    }

    // Get path from any source to any destination
    public void pairPath(int source, int dest) throws Exception{
        if (negativeCycles) {
            System.out.println("""
                    
                    Algorithm yielded incorrect results because graph contains negative cycles.
                    Restart the application with a new graph if you wish to run the algorithm.""");
            return;
        }
        if(dest<0 || dest>=currentGraph.getSize()) throw new Exception();
        if(source<0 || source>=currentGraph.getSize()) throw new Exception();
        Stack<Integer> path = new Stack<>();
        int current = dest;
        if(pairParents[source][current] == -1 && current != source) {   //check for path non-existence
            System.out.println("There is no path from node " + source + " to node " + dest);
            return;
        }
        //Push the path in reverse order in the stack
        do{
            path.push(current);
            current = pairParents[source][current];
        }while(current != source);
        path.push(source);
        //Pop the path back in correct order
        System.out.print("Shortest path from node " + source + " to node " + dest + ": ");
        while(!path.empty()) System.out.print(path.pop() + (path.size() > 0 ? " -> " : ""));
        System.out.println();
    }
}