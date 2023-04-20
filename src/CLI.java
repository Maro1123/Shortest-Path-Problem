import Graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

enum Running{
    init,
    singleSource,
    allPairs,
    negativeCycle
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
        currentGraph = new Graph(sc.nextLine());
        costs = new int[currentGraph.getSize()];
        parents = new int[currentGraph.getSize()];
        pairCosts = new int[currentGraph.getSize()][currentGraph.getSize()];
        pairParents = new int[currentGraph.getSize()][currentGraph.getSize()];
    }

    void getShortestPaths(){
        System.out.print("Please enter the source node: ");
        singleSource = sc.nextInt();
        System.out.println("Choose the method you would like to run to find all shortest paths from this node:" +
                            "\n 1. Dijkstra \n 2. Bellman-Ford \n 3. Floyd-Warshall");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                currentGraph.dijkstra(singleSource, costs, parents);
                break;
            case 2:
                //TODO
                //Bellman method call
                break;
            case 3:
//                currentGraph.fillAdjMatrix(pairCosts);
                currentGraph.floydWarshall(pairCosts, pairParents);
                break;
        }
    }

    void sourceDistance(int dest){
        System.out.println("Distance from node " + singleSource + " to node " + dest + " = " + costs[dest]);
    }

    void pairDistance(int source, int dest){
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
