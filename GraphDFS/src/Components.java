import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Components {

    private Graph graph;
    private int[] visited;
    private int ccNum; // connected components number

    public Components(Graph graph) {
        this.graph = graph;
        visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        // traverse different components
        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                dfs(v, ccNum);
                ccNum++;
            }
        }
    }

    private void dfs(int vertex, int ccid) {
        visited[vertex] = ccid;
        for (int w : graph.neighbors(vertex)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int getConnectedComponentNum() {
        return ccNum;
    }

    /**
     * detect if node v and node w is connected to each other
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v, int w) {
        return visited[v] == visited[w];
    }

    public List<Integer>[] getComponents() {
        List<Integer>[] result = new ArrayList[ccNum];
        for (int i = 0; i < ccNum; i++) {
            result[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            result[visited[v]].add(v);
        }
        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphDFS/graph.txt");
        Components components = new Components(graph);
        System.out.println("Connected components number: " + components.getConnectedComponentNum());
        System.out.println("Are the node 2 and the node 4 connected? " + components.isConnected(2, 4));
        System.out.println("Are the node 2 and the node 5 connected? " + components.isConnected(2, 5));
        List<Integer>[] comp = components.getComponents();
        for (int i = 0; i < comp.length; i++) {
            System.out.print("Components " + i + " : ");
            for (int w : comp[i]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
