import java.util.*;

public class PGS_가장먼노드 {
    public int solution(int n, int[][] edge) {
        // 1. 그래프 만들기
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {  // <-- 중요!
            graph.add(new ArrayList<>());
        }

        // 2. 양방향 그래프
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // 3. 거리 배열
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        dist[1] = 0;

        // 4. BFS
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.add(next);
                }
            }
        }

        // 5. 가장 먼 거리 찾기
        int maxDist = 0;
        for (int d : dist) {
            maxDist = Math.max(maxDist, d);
        }

        // 6. 가장 먼 노드 개수 세기
        int count = 0;
        for (int d : dist) {
            if (d == maxDist) count++;
        }

        return count;
    }
}
