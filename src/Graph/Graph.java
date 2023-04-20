package Graph;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {
    ArrayList<Edge>[] adjList;
    int size;

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
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < size; i++) {
            costs[i] = Integer.MAX_VALUE;
        }

        costs[source] = 0;
        pq.add(new Edge(source, costs[source]));
        parents[source] = -1; //source
        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            for (Edge i : adjList[cur.getTo()]) {
                if(costs[i.getTo()] > costs[cur.getTo()] + i.getWeight()){
                    costs[i.getTo()] = costs[cur.getTo()] + i.getWeight();
                    pq.add(new Edge(i.getTo(), costs[i.getTo()]));
                    parents[i.getTo()] = cur.getTo();
                }
            }
        }
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
        Graph g = new Graph("di.txt");
        System.out.println("hello");
        int[] dis = new int[g.size];
        int[] parent = new int[g.size];
        g.dijkstra(0,dis,parent);
        for (int i : dis) {
            System.out.print(i +" ");
        }
        System.out.println();
        for (int i : parent) {
            System.out.print(i +" ");
        }
    }
}
