public class CycleDetection {

    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getV()];
        // traverse different components
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break; // if a cycle already detected in the graph, break;
                }
            }
        }
    }

    /**
     * starting from the vertex to detect if there is a cycle in the graph.
     * if dfs(w, vertex) return true, means there is a cycle starting from w
     * so there is a cycle starting from vertex, we can return true.
     * @param vertex
     * @param parent
     * @return
     */
    private boolean dfs(int vertex, int parent) {
        visited[vertex] = true;
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                if (dfs(w, vertex)) {
                    return true;
                }
            } else if (w != parent){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());

        Graph graph2 = new Graph("GraphDFS/graph2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(graph2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
