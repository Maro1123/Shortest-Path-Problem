package Graph;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    ArrayList<ArrayList<Edge>> adjList;

    public Graph(String filename){
        try{
            Scanner scan = new Scanner(new File(filename));
            int v, e, start, dest, w;
            v = scan.nextInt();
            adjList = new ArrayList<>();
            for(int i=0 ; i<v ; i++) adjList.add(new ArrayList<>());
            e = scan.nextInt();
            for (int i=0 ; i<e ; i++){
                start = scan.nextInt();
                dest = scan.nextInt();
                w = scan.nextInt();
                adjList.get(start).add(new Edge(dest, w));
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



    boolean floydWarshall(int[][] costs, int[][] parents) {
        int n = costs.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (costs[i][j] > costs[i][k] + costs[k][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        parents[i][j] = k;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++)
            if (costs[i][i] < 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Graph g = new Graph("example.txt");
        System.out.println("hello");
    }
}
