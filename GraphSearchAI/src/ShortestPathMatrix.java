import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathMatrix {

    private int row;
    private int col;
    private int[][] dirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        if (grid[0][0] != 0 || grid[row - 1][col - 1] != 0) {
            return -1;
        }
        boolean[][] visited = new boolean[row][col];
        int[][] path = new int[row][col];

        //bfs
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        visited[0][0] = true;
        path[0][0] = 1;
        while (!queue.isEmpty()) {
            int point = queue.remove();
            int x = point / col;
            int y = point % col;
            if (x == row - 1 && y == col - 1) {
                return path[x][y];
            }
            for (int i = 0; i < 8; i++) {
                int nextx = x + dirs[i][0];
                int nexty = y + dirs[i][1];
                if (inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 0) {
                    visited[nextx][nexty] = true;
                    queue.add(nextx * col + nexty);
                    path[nextx][nexty] = path[x][y] + 1;
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][] {{0,0,0,0,1},{1,0,0,0,0},{0,1,0,1,0},{0,0,0,1,1},{0,0,0,1,0}};
        int[][] grid2 = new int[][] {{0,0,0,0,1},{1,0,0,0,0},{0,1,0,1,0}};
        ShortestPathMatrix shortestPathMatrix = new ShortestPathMatrix();
        System.out.println(shortestPathMatrix.shortestPathBinaryMatrix(grid1));
        System.out.println(shortestPathMatrix.shortestPathBinaryMatrix(grid2));
    }
}
