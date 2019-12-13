import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Path {

    private Graph graph;
    private int source;
    private int destination;
    private boolean[] visited;
    private int[] pre; // store the previous vertex of each vertex

    public Path(Graph graph, int source, int destination) {
        graph.validateVertex(source); // validate source vertex
        graph.validateVertex(destination); // validate destination vertex

        this.graph = graph;
        this.source = source;
        this.destination = destination;

        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        dfs(source, source);
    }

    private boolean dfs(int vertex, int parent) {
        visited[vertex] = true;
        pre[vertex] = parent;
        if (vertex == destination) {
            return true;
        }
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                if (dfs(w, vertex)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * if vertex destination is connected to the source, they are connected if t has been visited
     * @param
     * @return
     */
    public boolean isConnectedTo() {
        return visited[destination];
    }

    /**
     * get the path from source to destination
     * @param
     * @return
     */
    public Iterable<Integer> path() {
        List<Integer> result = new ArrayList<>();
        if (isConnectedTo()) {
            int cur = destination;
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
        Path path = new Path(graph, 0, 6);
        System.out.println("Path 0 -> 6: " + path.path());

        path = new Path(graph, 0, 1);
        System.out.println("Path 0 -> 6: " + path.path());

        path = new Path(graph, 0, 5);
        System.out.println("Path 0 -> 6: " + path.path());
    }
}
