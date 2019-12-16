import java.util.*;

public class SlidingPuzzle {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        String initialState = boardToString(board);
        if ("123450".equals(initialState)) {
            return 0;
        }

        // BFS
        queue.add(initialState);
        visited.put(initialState, 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();

            List<String> nexts = getNexts(cur);

            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if ("123450".equals(next)) {
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNexts(String s) {
        int[][] cur = stringToBoard(s);
        int zero ;
        for (zero = 0; zero < 6; zero++) {
            if (cur[zero / 3][zero % 3] == 0) {
                break;
            }
        }
        List<String> result = new ArrayList<>();
        int zx = zero / 3, zy = zero % 3;
        for (int d = 0; d < 4; d++) {
            int nextx = zx + dirs[d][0];
            int nexty = zy + dirs[d][1];
            if (inArea(nextx, nexty)) {
                swap(cur, zx, zy, nextx, nexty);
                result.add(boardToString(cur));
                swap(cur, zx, zy, nextx, nexty);
            }
        }
        return result;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] stringToBoard(String s) {
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i++) {
            board[i / 3][i % 3] = s.charAt(i) - '0';
        }
        return board;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {{4,1,2},{5,0,3}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.slidingPuzzle(board));
    }
}
