package tests;

import Graph.Graph;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Analysis {

    public static void main(String[] args) throws FileNotFoundException {
        for(int cnt = 10 ; cnt <= 400 ; cnt+=10) {
            Graph graph = new Graph("DataGeneratorAndAnalyzer/dense_analysis_graphs/" + cnt + "_graph.txt");
            long start = System.nanoTime();
            for (int i=0 ; i<20 ; i++)
                for (int j=0 ; j<200 ; j++)
                    graph.bellmanFord(j, new double[200], new int[200]);
            long end = System.nanoTime();
            long elapsed = (end - start)/20;
            System.out.println(elapsed);
        }
    }

    @Test
    void space() throws IOException {
        try {
            // Densities
            spaceDensityX();
            dijkstraSpaceDensity();
            bellmanSpaceDensity();
            floydSpaceDensity();

            // Sizes
            spaceSizeX();
            dijkstraSpaceSize();
            bellmanSpaceSize();
            floydSpaceSize();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //___________________________________________ Space Analysis _______________________________________________________

    void spaceDensityX() throws IOException {
        // Space Dijkstra Size X Data
        FileWriter writer = new FileWriter("plotting_data\\X_spaceDensity.txt");
        for (int i = 1; i <= 40; i++) {
            writer.write(i * 10 + "\n"); // avg_degree
        }
        writer.close();
    }

    void dijkstraSpaceDensity() throws IOException {
        // Space Dijkstra Density
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceDijDen.txt");
        for (int i = 1; i <= 40; i++) {
            writer.write(200 + i * 1000 + "\n"); // O(V + E) V = E / 2
        }
        writer.close();
    }

    void bellmanSpaceDensity() throws IOException {
        // Space Bellman Density
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceBellSDen.txt");
        for (int i = 1; i <= 40; i++) {
            writer.write(2 * 1000 * i + "\n"); // O(E)
        }
        writer.close();
    }

    void floydSpaceDensity() throws IOException {
        // Space Floyd Density
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceFlDen.txt");
        for (int i = 1; i <= 40; i++) {
            writer.write(200 * 200 + "\n"); // O(V * V)
        }
        writer.close();
    }

    void spaceSizeX() throws IOException {
        // Space Dijkstra Size X Data
        FileWriter writer = new FileWriter("plotting_data\\X_spaceSize.txt");
        for (int i = 50; i <= 3000; i += 50) {
            writer.write(i + "\n"); // num_nodes
        }
        writer.close();
    }

    void dijkstraSpaceSize() throws IOException {
        // Space Dijkstra Size Y data
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceDijSize.txt");
        for (int i = 50; i <= 3000; i += 50) {
            writer.write(3 * i + "\n"); // O(V + E) V = E / 2
        }
        writer.close();
    }

    void bellmanSpaceSize() throws IOException {
        // Space Bellman Size Y data
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceBellSize.txt");
        for (int i = 50; i <= 3000; i += 50) {
            writer.write(2 * i + "\n"); // O(E)
        }
        writer.close();
    }

    void floydSpaceSize() throws IOException {
        // Space Floyd Size Y data
        FileWriter writer = new FileWriter("plotting_data\\Y_spaceFlSize.txt");
        for (int i = 50; i <= 3000; i += 50) {
            writer.write(i * i + "\n"); // O(V * V)
        }
        writer.close();
    }

}
