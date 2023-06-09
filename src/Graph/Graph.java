package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    private final ArrayList<Edge>[] adjList;
    private final int size;
    private boolean negWeights;

    public Graph(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        int v, e, start, dest;
        double w;
        negWeights = false;
        v = scan.nextInt();
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
        size = v;
        e = scan.nextInt();
        for (int i = 0; i < e; i++) {
            start = scan.nextInt();
            dest = scan.nextInt();
            w = scan.nextDouble();
            if (w<0) negWeights = true;
            adjList[start].add(new Edge(dest, w));
        }
        scan.close();
    }

    public void dijkstra(int source, double[] costs, int[] parents) {
        if(negWeights) {
            Arrays.fill(costs,Double.POSITIVE_INFINITY);
            Arrays.fill(parents,-1);
            return;
        }

        init(source, costs, parents);

        boolean[] visited = new boolean[size];
        // Here we're using Edge only as an encapsulation of a node's index and the shortest distance
        // between it and the source being the weight.
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble((Edge A) -> A.weight));
        pq.add(new Edge(source, costs[source]));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            // May pop an old non-optimal distance for a visited node, and if it is visited,
            // the iteration is skipped, and we pop the next minimum distance node.
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            for (Edge i : adjList[cur.to]) {
                if(!visited[i.to] && costs[i.to] > costs[cur.to] + i.weight) {
                    costs[i.to] = costs[cur.to] + i.weight;
                    pq.add(new Edge(i.to, costs[i.to]));
                    parents[i.to] = cur.to;
                }
            }
        }
    }

    public boolean hasNegativeWeights(){
        return negWeights;
    }

    public boolean bellmanFord(int source, double[] costs, int[] parents) {
        init(source, costs, parents);

        boolean negCycle = false;

        for(int i = 0; i < size; i++) { // O(m * n)
            boolean changed = false;
            for(int j = 0; j < size; j++) { // parents
                for(Edge edge: adjList[j]) { // neighbours
                    int u = edge.to;
                    double w = edge.weight;
                    if(costs[j] != Double.POSITIVE_INFINITY && costs[j] + w < costs[u]) {
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
        return !negCycle;
    }

    public boolean floydWarshall(double[][] costs, int[][] parents) {
        floyd_init(costs, parents);

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (costs[i][k] != Double.POSITIVE_INFINITY && costs[k][j] != Double.POSITIVE_INFINITY && costs[i][k] + costs[k][j] < costs[i][j]) {
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

    private void init(int source, double[] costs, int[] parents) {
        Arrays.fill(costs, Double.POSITIVE_INFINITY);
        costs[source] = 0;
        Arrays.fill(parents, -1);
    }

    private void floyd_init(double[][] costs, int[][] parents) {
        for(double[] arr : costs)
            Arrays.fill(arr, Double.POSITIVE_INFINITY);
        for(int[] arr : parents)
            Arrays.fill(arr, -1);
        for(int i = 0; i < size; i++) {
            costs[i][i] = 0;
            parents[i][i] = -1;
            for(Edge edge : adjList[i]) {
                if(i == edge.to && edge.weight >= 0) //exclude the positive self cycles
                    continue;
                parents[i][edge.to] = i;
                costs[i][edge.to] = edge.weight;
            }
        }
    }

    public int getSize() {return size;}

}

