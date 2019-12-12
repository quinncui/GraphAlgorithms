import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdjacencyList {

    private int V;
    private int E;
    private List<Integer>[] adjacencyList;

    public AdjacencyList(String filename) {
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
            adjacencyList = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjacencyList[i] = new LinkedList<>();
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
                if (adjacencyList[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are detected!");
                }
                adjacencyList[a].add(b);
                adjacencyList[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
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
        return adjacencyList[v].contains(w);
    }

    /**
     * get neighbors of the vertex v
     * @param v
     * @return
     */
    public List<Integer> adj(int v) {
        validateVertex(v);
        return adjacencyList[v];
    }

    /**
     * get degree of the vertex v
     * @param v
     * @return
     */
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adjacencyList[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyList adjacencyList = new AdjacencyList("GraphBasics/ajMatrix.txt");
        System.out.println(adjacencyList);
    }
}