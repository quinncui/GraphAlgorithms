import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteDetection {

    private Graph graph;
    private boolean[] visited;

    private int[] colors;
    private boolean isBipartite;

    public BipartiteDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        colors = new int[graph.getV()];
        Arrays.fill(colors, -1);
        isBipartite = true;
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    public boolean bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        colors[vertex] = 0;

        while(!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                } else if (colors[w] == colors[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphBFS/graph.txt");
        BipartiteDetection bipartiteDetection = new BipartiteDetection(graph);
        System.out.println(bipartiteDetection.isBipartite());
    }
}
