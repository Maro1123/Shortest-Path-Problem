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

    void dijkestra(int source, int[] costs, int[] parents){
        //TODO
    }

    boolean bellmanFord(int source, int[] costs, int[] parents){
        //TODO
        return false;
    }

    boolean floydWarshall(int[][] costs, int[][] parents){
        //TODO
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph("example.txt");
        System.out.println("hello");
    }
}
