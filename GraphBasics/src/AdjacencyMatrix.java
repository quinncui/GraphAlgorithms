import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdjacencyMatrix {

    private int V;
    private int E;
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            E = scanner.nextInt();
            adjacencyMatrix = new int[V][V];
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adjacencyMatrix[a][b] = 1;
                adjacencyMatrix[b][a] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
