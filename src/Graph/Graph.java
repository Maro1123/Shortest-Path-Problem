package Graph;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;

public class Graph {
    ArrayList<Edge>[] adjList;
    int size;
    final int MAX = Integer.MAX_VALUE;
    
    // will be removed on removing bellman_init()
    public int[] costs, parents;

    public int[][] allCosts, allParents;

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
        parents[source] = -1; // -1 for the source node (has no parent)

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

    private boolean bellmanFord(int source, int[] costs, int[] parents){
        boolean negCycle = false;

        for(int i = 0; i < adjList.length; i++) { // O(m * n)
            boolean changed = false;
            for(int j = 0; j < adjList.length; j++) { // parents
                for(Edge edge: adjList[j]) { // neighbours
                    int u = edge.getTo(), w = edge.getWeight();
                    if(costs[j] != Integer.MAX_VALUE && costs[j] + w < costs[u]) {
                        changed = true;
                        if(i == adjList.length - 1)
                            negCycle = true;
                        costs[u] = costs[j] + w;
                        parents[u] = j;
                    }
                }
            }
            if(!changed) break;
        }
        return negCycle;
    }

    boolean floydWarshall() {
        int[][] costs = new int[size][size], parents = new int[size][size];
        for(int[] arr : costs)
            Arrays.fill(arr, Integer.MAX_VALUE);
        for(int i = 0; i < size; i++) {
            costs[i][i] = 0;
            for(Edge edge : adjList[i])
                costs[i][edge.getTo()] = edge.getWeight();
        }
        return floydWarshall(costs, parents);
    }

    public void fillAdjMatrix(int[][] matrix){
        for(int[] row : matrix) Arrays.fill(row,Integer.MAX_VALUE);
        for(int i=0 ; i<adjList.length ; i++){
            for(Edge edge : adjList[i]) matrix[i][edge.getTo()] = edge.getWeight();
        }
    }

    public boolean floydWarshall(int[][] costs, int[][] parents) {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE && costs[i][k] + costs[k][j] < costs[i][j]) {
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

    public boolean bellman_init(int source) {
        this.costs = new int[adjList.length];
        this.parents = new int[adjList.length];

        for(int i = 0; i < adjList.length; i++) {
            allCosts[i] = new int[adjList.length];
            allParents[i] = new int[adjList.length];
        }

        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(parents, Integer.MAX_VALUE);

        costs[source] = 0;
        parents[source] = -1;

        return bellmanFord(source, costs, parents);
    }

    public int getSize() {return adjList.length;}

}
