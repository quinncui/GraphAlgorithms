import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {

    private Graph graph;
    private boolean[] visited;
    private int[] pre;

    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        pre = new int[graph.getV()];
        Arrays.fill(pre, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean bfs(int vertex) {
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
                } else if (w != pre[v]) {
                    return true; // return in advance, no need to traverse the whole graph
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphBFS/graph.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());
    }
}
