package Graph;

public class Edge implements Comparable<Edge>{
    public int to;
    public int weight;
    public Edge(int to, int weight){
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.getWeight(); //min
    }
}
