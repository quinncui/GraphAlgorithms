import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphDFSIteration {

    private Graph graph;
    private boolean[] visited;
    // result of dfs
    private List<Integer> preorder = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();

    public GraphDFSIteration(Graph graph) {
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
        stack.push(vertex);
        visited[vertex] = true;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            preorder.add(cur);
            for (int v : graph.neighbors(cur)) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
    }

    public Iterable<Integer> getPreorder() {
        return preorder;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        GraphDFSIteration graphDFS = new GraphDFSIteration(graph);
        System.out.println(graphDFS.getPreorder());
    }
}
