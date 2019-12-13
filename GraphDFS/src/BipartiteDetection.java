import java.util.Arrays;

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
        // traverse different components
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int vertex, int color) {
        visited[vertex] = true;
        colors[vertex] = color;
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        BipartiteDetection bipartiteDetection = new BipartiteDetection(graph);
        System.out.println(bipartiteDetection.isBipartite());

        Graph graph3 = new Graph("GraphDFS/graph3.txt");
        BipartiteDetection bipartiteDetection3 = new BipartiteDetection(graph3);
        System.out.println(bipartiteDetection3.isBipartite());
    }
}
