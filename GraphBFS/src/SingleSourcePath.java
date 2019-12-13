import java.util.*;

public class SingleSourcePath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre;


    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        bfs(source ); // there is no path from source to other components
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        pre[vertex] = vertex;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

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
        Graph graph = new Graph("GraphBFS/graph.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        System.out.println(singleSourcePath.isConnectedTo(6));
        System.out.println(singleSourcePath.path(6));
    }
}
