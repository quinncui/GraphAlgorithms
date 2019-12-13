import java.util.*;

public class Path {

    private Graph graph;
    private int source, destination;

    private int[] pre;
    private boolean[] visited;

    public Path(Graph graph, int source, int destination) {
        graph.validateVertex(source);
        graph.validateVertex(destination);
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        bfs();

        for (boolean e : visited) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source;

        if (source == destination) {
            return;
        }

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    if (w == destination) {
                        return;
                    }
                }
            }
        }
    }

    public boolean isConnectedTo() {
        return visited[destination];
    }

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
        Graph graph = new Graph("GraphBFS/graph.txt");
        Path path = new Path(graph, 0, 6);
        System.out.println("Path 0 -> 6: " + path.path());

        path = new Path(graph, 0, 1);
        System.out.println("Path 0 -> 1: " + path.path());

        path = new Path(graph, 0, 5);
        System.out.println("Path 0 -> 5: " + path.path());
    }
}
