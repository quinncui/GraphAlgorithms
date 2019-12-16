import java.util.HashSet;
import java.util.Set;

public class FindArticulationPoints {

    private Graph graph;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int count;

    private Set<Integer> result;

    public FindArticulationPoints(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        ord = new int[graph.getV()];
        low = new int[graph.getV()];
        count = 0;

        result = new HashSet<>();

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

        int child = 0;
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                dfs(w, vertex);
                low[vertex] = Math.min(low[vertex], low[w]);
                child++;
                if (vertex == parent && child > 1) {
                    result.add(vertex);
                }
                if (vertex != parent && low[w] >= ord[vertex]) {
                    // vertex is an articulation points
                    result.add(vertex);
                }
            } else if (w != parent) {
                low[vertex] = Math.min(low[vertex], low[w]);
            }
        }
    }

    public Iterable<Integer> result() {
        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("BridgeAndArticulationPoint/graph.txt");
        FindArticulationPoints findArticulationPoints= new FindArticulationPoints(graph);
        System.out.println(findArticulationPoints.result());
        Graph graph2 = new Graph("BridgeAndArticulationPoint/graph2.txt");
        FindArticulationPoints findArticulationPoints2 = new FindArticulationPoints(graph2);
        System.out.println(findArticulationPoints2.result());
        Graph graph3 = new Graph("BridgeAndArticulationPoint/tree.txt");
        FindArticulationPoints findArticulationPoints3 = new FindArticulationPoints(graph3);
        System.out.println(findArticulationPoints3.result());
    }
}
