import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int[] dr = {1,0,-1,0};
        int[] dc = {0,-1,0,1};
        
        int n = maps.length;
        int m = maps[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            
            // 도착지 도달 시 return dist
            if (r==n-1 && c == m-1) return dist;
            
            // 다음 노드 예약
            for (int i = 0; i < 4 ; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // 배열 범위 안이고, 벽이 아니라 길이고, 좌표가 0,0 이상일 때
                if (nr < n && nc < m && nr >=0 && nc >=0 && maps[nr][nc]==1) {
                    if (!visited[nr][nc]){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr,nc,dist+1});
                    }
                }        
            }
        }   
        return -1;
    }
}