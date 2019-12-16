import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Graph {

    private int V;
    private int E;
    private HashSet<Integer>[] adjacencySet;

    public Graph(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative!");
            }
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative!");
            }
            adjacencySet = new HashSet[V];
            for (int i = 0; i < V; i++) {
                adjacencySet[i] = new HashSet<>();
            }
            // O(E*V)
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                // vertex a should be 0 <= a < V
                validateVertex(a);
                int b = scanner.nextInt();
                // vertex b should be 0 <= b < V
                validateVertex(b);
                // detect self loop of a and b
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                // detect parallel edges, worst case O(v)
                if (adjacencySet[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are detected!");
                }
                adjacencySet[a].add(b);
                adjacencySet[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adjacencySet[v].contains(w);
    }

    /**
     * get neighbors of the vertex v
     * @param v
     * @return
     */
    public Iterable<Integer> neighbors(int v) {
        validateVertex(v);
        return adjacencySet[v];
    }

    /**
     * get degree of the vertex v
     * @param v
     * @return
     */
    public int degree(int v) {
        validateVertex(v);
        return adjacencySet[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adjacencySet[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph adjacencySet = new Graph("GraphBasics/graph.txt");
        System.out.println(adjacencySet);
    }
}