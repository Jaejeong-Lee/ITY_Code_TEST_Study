import java.util.*;

public class LC_322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        queue.add(0);
        visited[0] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++; // 동전 하나 더 사용하는 경우

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                for (int coin : coins) {
                    int next = curr + coin;
                    if (next == amount) return level; // 목표 도달
                    if (next > amount || visited[next]) continue;

                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return -1; // 도달 불가능
    }
}
