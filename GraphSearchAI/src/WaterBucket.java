import java.util.*;

public class WaterBucket {

    private int[] pre;
    private int end = -1;

    public WaterBucket() {
        pre = new int[54];

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[54];

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int a = cur / 10;
            int b = cur % 10;
            // max a = 5, max b = 3
            List<Integer> nexts = new ArrayList<>();
            nexts.add(50 + b);
            nexts.add(a * 10 + 3);
            nexts.add(b);
            nexts.add(a * 10);
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + b + x);
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + b - y);

            for (Integer next : nexts) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;
                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result() {
        List<Integer> result = new ArrayList<>();
        if (end != -1) {
            int cur = end;
            while (cur != 0) {
                result.add(cur);
                cur = pre[cur];
            }
            result.add(0);
            Collections.reverse(result);
        }
        return result;
    }

    public static void main(String[] args) {
        WaterBucket waterBucket = new WaterBucket();
        System.out.println(waterBucket.result());
    }
}
