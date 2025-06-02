package week08.LC_Coin_Change;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        // bfs
        // 큐를 저장할 때, res, depth을 같이 저장
        Deque<int[]> queue = new ArrayDeque<>();
        // res에 대한 visited배열
        boolean[] visited = new boolean[amount+1];

        queue.add(new int[]{amount, 1});
        visited[amount] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.pop();
            for (int coin : coins) {
                int res = cur[0] - coin;

                    if (res == 0) return cur[1];
                    if (res < 0) continue;
                    if (res > 0) {
                        if(!visited[res]){
                        visited[res] = true;
                        queue.add(new int[]{res, cur[1] + 1});
                    }
                }
            }
        }
        return -1;
    }
}