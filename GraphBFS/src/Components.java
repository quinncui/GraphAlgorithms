import java.util.*;

public class Components {

    private Graph graph;
    private int[] visited;
    private int ccNum = 0;

    public Components(Graph graph) {
        this.graph = graph;
        visited = new int[graph.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < graph.getV(); v++) {
            if (visited[v] == -1) {
                bfs(v, ccNum);
                ccNum++;
            }
        }
    }

    public void bfs(int vertex, int ccid) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = ccid;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.neighbors(v)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccid;
                }
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
        Graph graph = new Graph("GraphBFS/graph.txt");
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
