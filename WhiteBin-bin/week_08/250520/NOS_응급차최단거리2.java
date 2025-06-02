import java.util.LinkedList;
import java.util.Queue;

public class NOS_응급차최단거리2 {
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 시작점 또는 도착점이 벽이면 탈락
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); // (x, y, 거리)
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], dist = curr[2];

            // 도착점에 도달하면 거리 리턴
            if (x == m - 1 && y == n - 1) {
                return dist;
            }

            // 8방향 탐색
            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 체크 + 방문 여부 + 벽인지 확인
                if (nx >= 0 && ny >= 0 && nx < m && ny < n &&
                        !visited[nx][ny] && grid[nx][ny] == 0) {

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        // 도착할 수 없음
        return -1;
    }
}
