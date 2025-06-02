package week_09;

import java.util.*;

public class PGS보물지도 {
    public int solution(int n, int m, int[][] hole) {
        /* 이동 방향 미리 정의해둠 */
        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};
        // 두 칸 점프했을 때 이동 방향
        int[] ddr = {2,0,-2,0};
        int[] ddc = {0,-2,0,2};

        Queue<State> queue = new ArrayDeque<>();
        int[][] maps = new int[m][n];
        // cols : x : 열 : n
        // rows : y : 행 : m
        // 지도에 함정 위치 미리 표시해둠
        for(int[] h: hole){
            maps[h[1]-1][h[0]-1] = 1;
        }
        /* visited 3차원 배열 설정 */
        // 방문 표시 && jump 여부 표시
        boolean[][][] visited = new boolean[m][n][2];
        // 초깃값 세팅
        queue.offer(new State(0,0,0,0));
        visited[0][0][0] = true;

        /* BFS 탐색 시작 */
        while (!queue.isEmpty()){
            // 현재 노드 방문
            State curr = queue.poll();

            /* 탈출 조건 */
            // 도착지에 왔으면 거리 반환
            if (curr.c == n-1 && curr.r == m-1 ) return curr.dist;

            /* 다음 노드 방문 예약 */
            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                // (0,0)좌표 이상이고, 지도 배열 안 벗어났고, 지나갈 수 있는 길이면,
                if(nr>=0 && nc >=0 && nr < m && nc < n && maps[nr][nc] == 0){
                    // 방문한 적 없으면
                    if (!visited[nr][nc][curr.jumped]){
                        visited[nr][nc][curr.jumped]= true;
                        queue.offer(new State(nr,nc,curr.dist+1, curr.jumped));
                    }
                }
            }

            /* 다음 노드를 가려는데 visited 다 했고, hole 때문에 막혔다면 */
            // => jump 해야하는 상황
            // 아직 점프 안 사용했으면 점프할 때 방향(ddr, ddc) 사용
            if(curr.jumped == 0){
                for (int i = 0; i < 4; i++) {
                    int nr = curr.r + ddr[i];
                    int nc = curr.c + ddc[i];

                    if(nr>=0 && nc >=0 && nr < m && nc < n && maps[nr][nc] == 0){
                        if (!visited[nr][nc][1]){
                            visited[nr][nc][1]= true;
                            queue.offer(new State(nr,nc,curr.dist+1, 1));
                        }
                    }
                }
            }
        }
        return -1;
    }
    class State{
        int r;
        int c;
        int dist;
        int jumped;
        State(int r , int c , int dist, int jumped){
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.jumped = jumped;
        }
    }
}