package week_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2206 {
    public static void main(String[] args) throws IOException {
        // 이동방향에 따른 x,y 좌표 변화 설정
        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};

        // 맵 생성
        // 1이면 벽, 0이면 길
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n =  Integer.parseInt(temp[0]);
        int m =  Integer.parseInt(temp[1]);
        int[][] map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String[] tempo = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(tempo[j - 1]);
            }
        }

        // 초깃값 세팅
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n+1][m+1][2];
        visited[1][1][0] = true;
        queue.offer(new int[]{1,1,1,0});
        // x, y, 최단거리, 벽 부순 여부

        while (!queue.isEmpty()) {
            // 현재 노드 꺼내기
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            int dist = cur[2];
            int broken = cur[3];

            // 탈출조건
            if (row == n && col == m) {
                System.out.println(dist);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (nr >=1 && nr <= n && nc>=1 && nc <= m) {
                    // 진행
                    if (map[nr][nc] == 0 && !visited[nr][nc][broken]) {
                        visited[nr][nc][broken] = true;
                        queue.offer(new int[]{nr, nc, dist + 1, broken});
                    } else if (map[nr][nc]==1 && broken == 0 && !visited[nr][nc][1]) {
                        // 벽 안 부수고 다 돌았는데도 최단거리가 나오지 않았다면,
                        // 벽이고, 아직 안 부쉈고, 부술 수 있다면,
                        visited[nr][nc][1] = true;
                        queue.offer(new int[]{nr, nc, dist + 1, 1});
                    }
                }
            }
        }
        System.out.println(-1);
    }
}