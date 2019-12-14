/**
 * leetcode 695 Max Area of Island
 */
public class MaxAreaIsland {

    private int R;
    private int C;
    private int[][] grid;
    private boolean[][] visited;
    private int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        R = grid.length;
        C = grid[0].length;

        this.grid = grid;
        visited = new boolean[R][C];

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    result = Math.max(result, dfs(i, j));
                }
            }
        }
        return result;
    }

    private int dfs(int r, int c) {
        visited[r][c] = true;
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int nextR = r + dirs[i][0];
            int nextC = c + dirs[i][1];
            if (inArea(nextR, nextC) && !visited[nextR][nextC] && grid[nextR][nextC] == 1) {
                result += dfs(nextR, nextC);
            }
        }
        return result;
    }

    private boolean inArea(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        MaxAreaIsland maxAreaIsland = new MaxAreaIsland();
        System.out.println(maxAreaIsland.maxAreaOfIsland(grid));
    }
}
