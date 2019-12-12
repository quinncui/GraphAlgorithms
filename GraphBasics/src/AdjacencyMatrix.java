import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrix {

    private int V;
    private int E;
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(String filename) {
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
            adjacencyMatrix = new int[V][V];
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
                // detect parallel edges
                if (adjacencyMatrix[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are detected!");
                }
                adjacencyMatrix[a][b] = 1;
                adjacencyMatrix[b][a] = 1;
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
        return adjacencyMatrix[v][w] == 1;
    }

    /**
     * get neighbors of the vertex v
     * @param v
     * @return
     */
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adjacencyMatrix[v][i] == 1) {
                result.add(i);
            }
        }
        return result;
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
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d", adjacencyMatrix[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("GraphBasics/ajMatrix.txt");
        System.out.println(adjacencyMatrix);
    }
}