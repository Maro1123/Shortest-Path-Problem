package Graph;

public class Edge implements Comparable<Edge>{

    final int to;
    final int weight;
    public Edge(int to, int weight){
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight; //min
    }

}
