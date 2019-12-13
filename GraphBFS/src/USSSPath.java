import java.util.*;

/**
 * Unweighted Single Source Shortest Path
 */
public class USSSPath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] pre;
    private int[] distance;

    public USSSPath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        distance = new int[graph.getV()];
        Arrays.fill(pre, -1);
        Arrays.fill(distance, -1);
        bfs(source ); // there is no path from source to other components

        for (int i = 0; i < graph.getV(); i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println();
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        pre[vertex] = vertex;
        distance[vertex] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    distance[w] = distance[v] + 1;
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

    public int distance(int t) {
        graph.validateVertex(t);
        return distance[t];
    }


    public static void main(String[] args) {
        Graph graph = new Graph("GraphBFS/graph.txt");
        System.out.println("0 -> 6 shortest path: ");
        USSSPath usssPath = new USSSPath(graph, 0);
        System.out.println(usssPath.isConnectedTo(6));
        System.out.println(usssPath.path(6));
        System.out.println(usssPath.distance(6));
        Graph graph2 = new Graph("GraphBFS/graph2.txt");
        System.out.println("0 -> 5shortest path: ");
        USSSPath usssPath2 = new USSSPath(graph2, 0);
        System.out.println(usssPath2.isConnectedTo(5));
        System.out.println(usssPath2.path(5));
        System.out.println(usssPath2.distance(5));
    }
}
