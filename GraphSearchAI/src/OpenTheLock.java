import java.util.*;

public class OpenTheLock {

    public int openLock(String[] deadends, String target) {

        Set<String> deadset = new HashSet<>();
        for (String dead : deadends) {
            deadset.add(dead);
        }

        if (deadset.contains("0000") || deadset.contains(target)) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }

        //BFS
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        queue.add("0000");
        visited.put("0000", 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();
            char[] curArray = cur.toCharArray();
            List<String> nexts = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                char origin = curArray[i];
                curArray[i] = Character.forDigit((curArray[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curArray));
                curArray[i] = Character.forDigit((curArray[i] - '0' + 8) % 10, 10);
                nexts.add(new String(curArray));
                curArray[i] = origin;
            }
            for (String next : nexts) {
                if (!deadset.contains(next) && !visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if (next.equals(target)) {
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = new String[] {"0201","0101","0102","1212","2002"};
        OpenTheLock openTheLock = new OpenTheLock();
        System.out.println(openTheLock.openLock(deadends, "0202"));
    }
}
