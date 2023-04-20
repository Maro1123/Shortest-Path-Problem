package Graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {
    ArrayList<Edge>[] adjList;
    int size;
    final int MAX = Integer.MAX_VALUE;

    public Graph(String filename){
        try{
            Scanner scan = new Scanner(new File(filename));
            int v, e, start, dest, w;
            v = scan.nextInt();
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<Edge>();
            }
            size = v;
            e = scan.nextInt();
            for (int i=0 ; i<e ; i++){
                start = scan.nextInt();
                dest = scan.nextInt();
                w = scan.nextInt();
                adjList[start].add(new Edge(dest, w));
            }
            scan.close();
        }catch(Exception e){
            System.out.println("nope");
        }
    }

    void dijkstra(int source, int[] costs, int[] parents){
        //TODO
    }

    boolean bellmanFord(int source, int[] costs, int[] parents){
        //TODO
        return false;
    }

    boolean floydWarshall() {
        int[][] costs = new int[size][size], parents = new int[size][size];
        for(int[] arr : costs)
            Arrays.fill(arr, MAX);
        for(int i = 0; i < size; i++) {
            costs[i][i] = 0;
            for(Edge edge : adjList[i])
                costs[i][edge.to] = edge.weight;
        }
        return floydWarshall(costs, parents);
    }

    boolean floydWarshall(int[][] costs, int[][] parents) {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (costs[i][k] != MAX && costs[k][j] != MAX && costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        parents[i][j] = k;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++)
            if (costs[i][i] < 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Graph g = new Graph("example.txt");
        System.out.println("hello");
    }
}
