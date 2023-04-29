package Graph;

public class Edge {

    final int to;
    final double weight;
    public Edge(int to, double weight){
        this.to = to;
        this.weight = weight;
    }

//    @Override
//    public int compareTo(Edge o) {
//        double diff = weight - o.weight;
//        if (Math.abs(diff) < 0.00001) return 0;
//        if (diff < 0) return -1;
//        return 1;
//    }

}
