import java.util.*;

public class PGS_미로탈출 {
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int n, m;

    public int solution(String[] maps) {
        n = maps.length;         // 행 개수
        m = maps[0].length();    // 열 개수

        // 1. String[] → char[][] 변환
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = maps[i].toCharArray();
        }

        // 2. 시작점(S), 레버(L), 출구(E) 좌표 찾기
        int[] start = new int[2], lever = new int[2], end = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'S') start = new int[]{i, j};
                if (grid[i][j] == 'L') lever = new int[]{i, j};
                if (grid[i][j] == 'E') end = new int[]{i, j};
            }
        }

        // 3. S → L 거리
        int dist1 = bfs(grid, start, 'L');
        if (dist1 == -1) return -1;

        // 4. L → E 거리
        int dist2 = bfs(grid, lever, 'E');
        if (dist2 == -1) return -1;

        return dist1 + dist2;
    }

    // BFS 함수: 시작점 → 목표 문자까지 최단 거리 탐색
    private int bfs(char[][] grid, int[] start, char target) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], dist = curr[2];

            if (grid[x][y] == target) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && grid[nx][ny] != 'X') {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }

        return -1; // 목표까지 도달 불가
    }
}
