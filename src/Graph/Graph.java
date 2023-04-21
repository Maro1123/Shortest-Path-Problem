package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    private ArrayList<Edge>[] adjList;
    private int size;

    public Graph(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        int v, e, start, dest, w;
        v = scan.nextInt();
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList();
        }
        size = v;
        e = scan.nextInt();
        for (int i = 0; i < e; i++) {
            start = scan.nextInt();
            dest = scan.nextInt();
            w = scan.nextInt();
            adjList[start].add(new Edge(dest, w));
        }
        scan.close();
    }

    public void dijkstra(int source, int[] costs, int[] parents) {
        init(source, costs, parents);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, costs[source]));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for (Edge i : adjList[cur.getTo()]) {
                if(costs[cur.getTo()] != Integer.MAX_VALUE && costs[i.getTo()] > costs[cur.getTo()] + i.getWeight()) {
                    costs[i.getTo()] = costs[cur.getTo()] + i.getWeight();
                    pq.add(new Edge(i.getTo(), costs[i.getTo()]));
                    parents[i.getTo()] = cur.getTo();
                }
            }
        }
    }

    public boolean bellmanFord(int source, int[] costs, int[] parents) {
        init(source, costs, parents);

        boolean negCycle = false;

        for(int i = 0; i < size; i++) { // O(m * n)
            boolean changed = false;
            for(int j = 0; j < size; j++) { // parents
                for(Edge edge: adjList[j]) { // neighbours
                    int u = edge.getTo(), w = edge.getWeight();
                    if(costs[j] != Integer.MAX_VALUE && costs[j] + w < costs[u]) {
                        changed = true;
                        if(i == size - 1)
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

    public boolean floydWarshall(int[][] costs, int[][] parents) {
        floyd_init(costs, parents);
        
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE && costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        parents[i][j] = parents[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < size; i++)
            if (costs[i][i] < 0)
                return false;
        return true;
    }

    private void init(int source, int[] costs, int[] parents) {
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[source] = 0;
        parents[source] = -1;
    }

    private void floyd_init(int[][] costs, int[][] parents) {
        for(int[] arr : costs)
            Arrays.fill(arr, Integer.MAX_VALUE);
        for(int i = 0; i < size; i++) {
            costs[i][i] = 0;
            parents[i][i] = -1;
            for(Edge edge : adjList[i]) {
                parents[i][edge.getTo()] = i;
                costs[i][edge.getTo()] = edge.getWeight();
            }
        }
    }

    public int getSize() {return size;}

}
