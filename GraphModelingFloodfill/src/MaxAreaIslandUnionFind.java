/**
 * leetcode 695 Max Area of Island Solved by Union Find
 */
public class MaxAreaIslandUnionFind {

    private class UF {

        private int[] parent;
        private int[] size;

        public UF(int n) {

            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void unionElements(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) {
                return;
            }
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }

        public int size(int p) {
            return size[find(p)];
        }
    }

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        R = grid.length;
        C = grid[0].length;

        UF uf = new UF(R * C);

        for (int v = 0; v < R * C; v++) {
            int x = v / C;
            int y = v % C;
            if (grid[x][y] == 1) {
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        uf.unionElements(v, next);
                    }
                }
            }
        }

        int result = 0;
        for (int v = 0; v < R * C; v++) {
            int x = v / C;
            int y = v % C;
            if (grid[x][y] == 1) {
                result = Math.max(result, uf.size(v));
            }
        }
        return result;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        MaxAreaIslandUnionFind maxAreaIsland = new MaxAreaIslandUnionFind();
        System.out.println(maxAreaIsland.maxAreaOfIsland(grid));
    }
}
