import java.util.Arrays;

/**
 * leetcode 785 Is Graph Bipartite
 */
public class IsBipartite {
    private int[] colors;

    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        colors = new int[len];
        Arrays.fill(colors, -1);
        for (int v = 0; v < len; v++) {
            if (colors[v] == -1) {
                if (!dfs(graph, v, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int source, int color) {
        colors[source] = color;
        for (int v : graph[source]) {
            if (colors[v] == -1) {
                if (!dfs(graph, v, 1 - color)) {
                    return false;
                }
            } else if (colors[v] == colors[source]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] {{1,3},{0,2},{1,3},{0,2}};
        IsBipartite isBipartite = new IsBipartite();
        System.out.println(isBipartite.isBipartite(graph));
    }
}
