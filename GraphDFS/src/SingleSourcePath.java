import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SingleSourcePath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre; // store the previous vertex of each vertex

    public SingleSourcePath(Graph graph, int source) {
        graph.validateVertex(source); // validate source vertex

        this.graph = graph;
        this.source = source;

        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        dfs(source, source); // there is no path from the source to other components, so no need to explore other components
    }

    private void dfs(int vertex, int parent) {
        visited[vertex] = true;
        pre[vertex] = parent;
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                dfs(w, vertex);
            }
        }
    }

    /**
     * if vertex t is connected to the source, they are connected if t has been visited
     * @param t
     * @return
     */
    public boolean isConnectedTo(int t) {
        graph.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        List<Integer> result = new ArrayList<>();
        if (isConnectedTo(t)) {
            int cur = t;
            while (cur != source) {
                result.add(cur);
                cur = pre[cur];
            }
            result.add(source);
            Collections.reverse(result);
        }
        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        System.out.println("Path 0 -> 5: " + singleSourcePath.path(5));
        System.out.println("Path 0 -> 6: " + singleSourcePath.path(6));
    }
}
