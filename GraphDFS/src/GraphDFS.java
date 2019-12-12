import java.util.ArrayList;
import java.util.List;

public class GraphDFS {

    private Graph graph;
    private boolean[] visited;
    // result of dfs
    private List<Integer> preorder = new ArrayList<>();
    private List<Integer> postorder = new ArrayList<>();

    public GraphDFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        // traverse different components
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int vertex) {
        visited[vertex] = true;
        // preorder of traversal
        preorder.add(vertex);
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        // postorder of traversal
        postorder.add(vertex);
    }

    public Iterable<Integer> getPreorder() {
        return preorder;
    }

    public Iterable<Integer> getPostorder() {
        return postorder;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.getPreorder());
        System.out.println(graphDFS.getPostorder());
    }
}
