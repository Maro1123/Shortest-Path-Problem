# Shortest Path Problem With 3 Algorithms

- This project includes a graph ADT that supports 3 shortest path algorithms, which are Dijkstra’s algorithm, the Bellman-Ford algorithm, and the Floyd-Warshall algorithm.

- We also implemented a command line interface that allows the user to read the graph they want to operate on from a local file they specify and operate on that graph using the mentioned algorithms.

- The user can ask the program for the shortest path between two nodes or the distance between them, and they can also ask if there are negative cycles in the graph using the Bellman-Ford algorithm or the Floyd-Warshall algorithm.


# Time Complexity Analysis

- For finding all shortest paths between a single source node and every other node in the graph, Dijkstra's algorithm is 𝑶((𝑽+𝑬)𝐥𝐨𝐠(𝑽)), and Bellman-Ford's algorithm is 𝑶(𝑽𝑬).

- For finding shortest paths between all pairs of nodes, Dijkstra's algorithm is 𝑶((𝑽^𝟐+𝑽𝑬)𝐥𝐨𝐠(𝑽)), Bellman-Ford's algorithm is 𝑶(𝑽^𝟐𝑬), amd Floyd-Warshall's algorithm is 𝑶(𝑽^3)
