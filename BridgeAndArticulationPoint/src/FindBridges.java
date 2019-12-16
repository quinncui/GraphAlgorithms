import java.util.ArrayList;
import java.util.List;

public class FindBridges {

    private Graph graph;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private List<Edge> result;
    private int count;

    public FindBridges(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        ord = new int[graph.getV()];
        low = new int[graph.getV()];
        count = 0;

        result = new ArrayList<>();

        // traverse different components
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int vertex, int parent) {
        visited[vertex] = true;
        ord[vertex] = count;
        low[vertex] = count;
        count++;

        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                dfs(w, vertex);
                low[vertex] = Math.min(low[vertex], low[w]);
                if (low[w] > ord[vertex]) {
                    // v-w is a bridge
                    result.add(new Edge(vertex, w));
                }
            } else if (w != parent) {
                low[vertex] = Math.min(low[vertex], low[w]);
            }
        }
    }

    public List<Edge> result() {
        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("BridgeAndArticulationPoint/graph.txt");
        FindBridges findBridges = new FindBridges(graph);
        System.out.println(findBridges.result());
        Graph graph2 = new Graph("BridgeAndArticulationPoint/graph2.txt");
        FindBridges findBridges2 = new FindBridges(graph2);
        System.out.println(findBridges2.result());
        Graph graph3 = new Graph("BridgeAndArticulationPoint/tree.txt");
        FindBridges findBridges3 = new FindBridges(graph3);
        System.out.println(findBridges3.result());
    }
}
