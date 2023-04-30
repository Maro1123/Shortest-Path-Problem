package tests;
import Graph.Graph;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Arrays;
import static tests.ExpectedValues.expectedCosts1;
import static tests.ExpectedValues.expectedCosts2;
import static tests.ExpectedValues.expectedCosts3;
import static tests.ExpectedValues.expectedCosts4;
import static tests.ExpectedValues.expectedCosts5;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    static int[] singleSourceParents;
    static double[] singleSourceCosts;
    static int[][] pairParents;
    static double[][] pairCosts;
    static int vertices;

    @Test
    void positiveEdgeDijkstra1(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G1_5_25.txt");
            for(int i=0 ; i<vertices ; i++) g.dijkstra(i, pairCosts[i], pairParents[i]);
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts1[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeBellman1(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G1_5_25.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts1[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeFloyd1(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G1_5_25.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts1[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeDijkstra2(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G2_10_20.txt");
            for(int i=0 ; i<vertices ; i++) g.dijkstra(i, pairCosts[i], pairParents[i]);
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts2[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeBellman2(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G2_10_20.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts2[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeFloyd2(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G2_10_20.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts2[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeDijkstra3(){
        try {
            vertices = 30;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G3_30_90.txt");
            for(int i=0 ; i<vertices ; i++) g.dijkstra(i, pairCosts[i], pairParents[i]);
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts3[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeBellman3(){
        try {
            vertices = 30;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G3_30_90.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts3[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void positiveEdgeFloyd3(){
        try {
            vertices = 30;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G3_30_90.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts3[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeDijkstra1(){
        try{
            vertices = 10;
            singleSourceParents = new int[vertices];
            singleSourceCosts = new double[vertices];
            double[] dijkstraErrorCosts = new double[vertices];
            int[] dijkstraErrorParents = new int[vertices];
            Arrays.fill(dijkstraErrorCosts, Double.POSITIVE_INFINITY);
            Arrays.fill(dijkstraErrorParents,-1);
            Graph g = new Graph("tests\\01G4_10_20.txt");
            g.dijkstra(0, singleSourceCosts, singleSourceParents);
            assertArrayEquals(dijkstraErrorCosts, singleSourceCosts);
            assertArrayEquals(dijkstraErrorParents, singleSourceParents);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeBellman1(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\01G4_10_20.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts4[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeFloyd1(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\01G4_10_20.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts4[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeDijkstra2(){
        try{
            vertices = 30;
            singleSourceParents = new int[vertices];
            singleSourceCosts = new double[vertices];
            double[] dijkstraErrorCosts = new double[vertices];
            int[] dijkstraErrorParents = new int[vertices];
            Arrays.fill(dijkstraErrorCosts, Double.POSITIVE_INFINITY);
            Arrays.fill(dijkstraErrorParents,-1);
            Graph g = new Graph("tests\\01G5_30_84.txt");
            g.dijkstra(0, singleSourceCosts, singleSourceParents);
            assertArrayEquals(dijkstraErrorCosts, singleSourceCosts);
            assertArrayEquals(dijkstraErrorParents, singleSourceParents);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeBellman2(){
        try {
            vertices = 30;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\01G5_30_84.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts5[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeEdgeFloyd2(){
        try {
            vertices = 30;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\01G5_30_84.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(expectedCosts5[i], pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeSelfLoopDijkstra(){
        try{
            vertices = 5;
            singleSourceParents = new int[vertices];
            singleSourceCosts = new double[vertices];
            double[] dijkstraErrorCosts = new double[vertices];
            int[] dijkstraErrorParents = new int[vertices];
            Arrays.fill(dijkstraErrorCosts, Double.POSITIVE_INFINITY);
            Arrays.fill(dijkstraErrorParents,-1);
            Graph g = new Graph("tests\\11G6_5_25.txt");
            g.dijkstra(0, singleSourceCosts, singleSourceParents);
            assertArrayEquals(dijkstraErrorCosts, singleSourceCosts);
            assertArrayEquals(dijkstraErrorParents, singleSourceParents);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeSelfLoopBellman(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G6_5_25.txt");
            for(int i=0 ; i<vertices ; i++)
                assertFalse(g.bellmanFord(i, pairCosts[i], pairParents[i]));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeSelfLoopFloyd(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G6_5_25.txt");
            assertFalse(g.floydWarshall(pairCosts, pairParents));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeLoopsDijkstra1(){
        try{
            vertices = 10;
            singleSourceParents = new int[vertices];
            singleSourceCosts = new double[vertices];
            double[] dijkstraErrorCosts = new double[vertices];
            int[] dijkstraErrorParents = new int[vertices];
            Arrays.fill(dijkstraErrorCosts, Double.POSITIVE_INFINITY);
            Arrays.fill(dijkstraErrorParents,-1);
            Graph g = new Graph("tests\\11G7_10_20.txt");
            g.dijkstra(0, singleSourceCosts, singleSourceParents);
            assertArrayEquals(dijkstraErrorCosts, singleSourceCosts);
            assertArrayEquals(dijkstraErrorParents, singleSourceParents);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeLoopsBellman1(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G7_10_20.txt");
            for(int i=0 ; i<vertices ; i++)
                assertFalse(g.bellmanFord(i, pairCosts[i], pairParents[i]));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeLoopsFloyd1(){
        try {
            vertices = 10;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G7_10_20.txt");
            assertFalse(g.floydWarshall(pairCosts, pairParents));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeLoopsBellman2(){
        try {
            vertices = 6;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G8_6_8.txt");
            for(int i=0 ; i<vertices ; i++)
                assertFalse(g.bellmanFord(i, pairCosts[i], pairParents[i]));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void negativeLoopsFloyd2(){
        try {
            vertices = 6;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G8_6_8.txt");
            assertFalse(g.floydWarshall(pairCosts, pairParents));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void OneNegativeLoopBellman(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G9_5_25.txt");
            for(int i=0 ; i<vertices ; i++)
                assertFalse(g.bellmanFord(i, pairCosts[i], pairParents[i]));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void OneNegativeLoopFloyd(){
        try {
            vertices = 5;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G9_5_25.txt");
            assertFalse(g.floydWarshall(pairCosts, pairParents));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void singlePositiveNodeDijkstra(){
        try {
            vertices = 1;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G10_1_1.txt");
            for(int i=0 ; i<vertices ; i++) g.dijkstra(i, pairCosts[i], pairParents[i]);
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(new double[]{0}, pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void singlePositiveNodeBellman(){
        try {
            vertices = 1;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G10_1_1.txt");
            for(int i=0 ; i<vertices ; i++)
                assertTrue(g.bellmanFord(i, pairCosts[i], pairParents[i]));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(new double[]{0}, pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void singlePositiveNodeFloyd(){
        try {
            vertices = 1;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\00G10_1_1.txt");
            assertTrue(g.floydWarshall(pairCosts, pairParents));
            for(int i=0 ; i<vertices ; i++)
                assertArrayEquals(new double[]{0}, pairCosts[i]);
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void singleNegativeNodeBellman(){
        try {
            vertices = 1;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G11_1_1.txt");
            for(int i=0 ; i<vertices ; i++)
                assertFalse(g.bellmanFord(i, pairCosts[i], pairParents[i]));
        }catch(FileNotFoundException e){
            fail();
        }
    }

    @Test
    void singleNegativeNodeFloyd(){
        try {
            vertices = 1;
            pairParents = new int[vertices][vertices];
            pairCosts = new double[vertices][vertices];
            Graph g = new Graph("tests\\11G11_1_1.txt");
            assertFalse(g.floydWarshall(pairCosts, pairParents));
        }catch(FileNotFoundException e){
            fail();
        }
    }
}
