import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {

    private Graph graph;
    private  boolean[] visited;

    private List<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphBFS/graph.txt");
        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println("BFS Order : " + graphBFS.getOrder());
    }
}
