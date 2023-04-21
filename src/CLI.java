import Graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

enum Running{
    init,
    ssDijkstra,
    ssBellman,
    ssFloydWarshall,
    apDijkstra,
    apBellman,
    apFloydWarshall,
    ncBellman,
    ncFloydWarshall
}

public class CLI {
    Running state;
    Graph currentGraph;
    int singleSource;
    int[] costs, parents;
    int[][] pairCosts, pairParents;
    Scanner sc;

    public CLI(){
        state = Running.init;
        sc = new Scanner(System.in);
    }

    void initialize(){
        System.out.println("Welcome. Please Enter the name of the file that contains the graph data:");
        while(true){
            try{
                currentGraph = new Graph(sc.nextLine());
                break;
            }catch (FileNotFoundException e){
                System.out.println("File not found, please try again.");
            }
        }
        setArrays();
    }

    public void setArrays(){
        costs = new int[currentGraph.getSize()];
        parents = new int[currentGraph.getSize()];
        pairCosts = new int[currentGraph.getSize()][currentGraph.getSize()];
        pairParents = new int[currentGraph.getSize()][currentGraph.getSize()];
    }

    public void setupOperation(){
        int choice;
        while(true){
            System.out.println("Choose the operation you want to run:\n" +
                    " 1. Shortest path from a single node \n 2. Shortest path between all nodes \n 3. Check for negative cycles");
            try {
                choice = sc.nextInt();
                if (choice == 1) {
                    getShortestPaths();
                    break;
                }
                else if (choice == 2) {
                    getAllShortestPaths();
                    break;
                }
                else if (choice == 3) {
                    checkNegativeCycles();
                    break;
                }
                else {
                    System.out.println("Invalid input! try again.");
                }
            }catch(Exception e){
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    void getShortestPaths(){
        int choice;
        System.out.print("Please enter the source node: ");
        singleSource = sc.nextInt();
        System.out.println("Choose the method you would like to run to find all shortest paths from this node:" +
                            "\n 1. Dijkstra \n 2. Bellman-Ford \n 3. Floyd-Warshall");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {
                    currentGraph.dijkstra(singleSource, costs, parents);
                    state = Running.ssDijkstra;
                    break;
                }
                else if (choice == 2) {
                    currentGraph.bellmanFord(singleSource, costs, parents);
                    state = Running.ssBellman;
                    break;
                }
                else if (choice == 3) {
                    currentGraph.floydWarshall(pairCosts, pairParents);
                    state = Running.ssFloydWarshall;
                    break;
                }
                else {
                    System.out.println("Invalid input! try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
    }

    void getAllShortestPaths(){
        int choice;
        System.out.println("Choose the method you would like to run to find all shortest paths between all pairs of nodes:" +
                "\n 1. Dijkstra \n 2. Bellman-Ford \n 3. Floyd-Warshall");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {
                    for (int i=0 ; i<currentGraph.getSize() ; i++)
                        currentGraph.dijkstra(i, pairCosts[i], pairParents[i]);
                    state = Running.apDijkstra;
                    break;
                }
                else if (choice == 2) {
                    for (int i=0 ; i<currentGraph.getSize() ; i++)
                        currentGraph.bellmanFord(i, pairCosts[i], pairParents[i]);
                    state = Running.apBellman;
                    break;
                }
                else if (choice == 3) {
                    currentGraph.floydWarshall(pairCosts, pairParents);
                    state = Running.apFloydWarshall;
                    break;
                }
                else {
                    System.out.println("Invalid input! try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
    }

    void checkNegativeCycles(){
        int choice;
        boolean negCycles;
        System.out.println("Choose the method you would like to run to check if there are negative cycles:" +
                "\n 1. Bellman-Ford \n 2. Floyd-Warshall");
        while(true) {
            try{
                choice = sc.nextInt();
                if (choice == 1) {
                    negCycles = currentGraph.bellmanFord(0, costs, parents);
                    state = Running.ncBellman;
                    break;
                }
                else if (choice == 2) {
                    negCycles = currentGraph.floydWarshall(pairCosts, pairParents);
                    state = Running.ncFloydWarshall;
                    break;
                }
                else {
                    System.out.println("Invalid input! try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid input! try again.");
            }
        }
        if(negCycles) System.out.println("The graph has negative cycles.");
        else System.out.println("The graph does not have negative cycles.");
    }

    public void distance(int source, int dest){
        if (state == Running.ssBellman || state == Running.ssDijkstra)
            System.out.println("Distance from node " + singleSource + " to node " + dest + " = " + costs[dest]);
        if (state == Running.ssFloydWarshall)
            System.out.println("Distance from node " + singleSource + " to node " + dest + " = " + pairCosts[singleSource][dest]);
        else if (state == Running.apFloydWarshall || state == Running.apDijkstra || state == Running.apBellman)
            System.out.println("Distance from node " + source + " to node " + dest + " = " + pairCosts[source][dest]);
    }

    void sourcePath(int dest){
        Stack<Integer> path = new Stack<>();
        int current = dest;
        //Push the path in reverse order in the stack
        do{
            path.push(current);
            current = parents[current];
        }while(current != singleSource);
        path.push(singleSource);
        //Pop the path back in correct order
        System.out.println("Shortest path from node " + singleSource + " to node " + dest + ":");
        while(!path.empty()) System.out.println(path.pop());
    }

    void pairPath(int source, int dest){
        Stack<Integer> path = new Stack<>();
        int current = dest;
        //Push the path in reverse order in the stack
        do{
            path.push(current);
            current = pairParents[source][current];
        }while(current != source);
        path.push(source);
        //Pop the path back in correct order
        System.out.println("Shortest path from node " + source + " to node " + dest + ":");
        while(!path.empty()) System.out.println(path.pop());
    }
}
