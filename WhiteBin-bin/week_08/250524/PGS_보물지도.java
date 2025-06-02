import java.util.*;

public class PGS_보물지도 {
    static final int[][] DIRECTIONS = {{-1,0}, {0,-1}, {1,0}, {0,1}}; // 상, 좌, 하, 우 방향

    static class State {
        int x, y, jumpUsed, time;
        State(int x, int y, int jumpUsed, int time) {
            this.x = x;
            this.y = y;
            this.jumpUsed = jumpUsed; // 신발 사용 여부 (0 or 1)
            this.time = time;         // 이동 시간(거리)
        }
    }

    public int solution(int n, int m, int[][] hole) {
        boolean[][] trap = new boolean[m][n];  // 함정 위치 표시 배열
        for (int[] h : hole) {
            trap[m - h[1]][h[0] - 1] = true;   // 좌표 변환 후 함정 표시
        }

        boolean[][][] visited = new boolean[m][n][2]; // 방문 기록: [x][y][신발사용여부]
        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(m - 1, 0, 0, 0));  // 출발점 (아래 왼쪽)
        visited[m - 1][0][0] = true;

        while (!q.isEmpty()) {
            State curr = q.poll();

            // 보물 위치 도착 시 현재 시간 반환
            if (curr.x == 0 && curr.y == n - 1) return curr.time;

            for (int[] dir : DIRECTIONS) {
                for (int step = 1; step <= 2; step++) { // 1칸 이동 or 2칸 점프 시도
                    int nx = curr.x + dir[0] * step;
                    int ny = curr.y + dir[1] * step;
                    int nextJumpUsed = curr.jumpUsed + (step == 2 ? 1 : 0);

                    // 범위 벗어나거나, 함정이거나, 신발 사용 초과, 이미 방문한 상태면 skip
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                    if (trap[nx][ny] || nextJumpUsed > 1 || visited[nx][ny][nextJumpUsed]) continue;

                    visited[nx][ny][nextJumpUsed] = true;
                    q.offer(new State(nx, ny, nextJumpUsed, curr.time + 1));
                }
            }
        }

        return -1;  // 보물에 도달하지 못할 경우
    }
}
