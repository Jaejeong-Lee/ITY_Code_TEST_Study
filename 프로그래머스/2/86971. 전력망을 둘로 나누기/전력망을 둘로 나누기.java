import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        for (int i = 0;i<wires.length ; i++){
            // 탐색할 인접 그래프 생성
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int j = 1; j <= n ; j++){
                map.put(j, new ArrayList<>());
            }    
            
            for (int j = 0; j < wires.length; j++){
                // i번째 전선 끊기
                if (i==j) continue;
                int a = wires[j][0];
                int b = wires[j][1];
                map.get(a).add(b);
                map.get(b).add(a);
            }
            
            boolean[] visited = new boolean[n+1];
            int count = dfs(map,visited,1);
            int diff = Math.abs(n-count-count);
            answer = Math.min(answer,diff);
            
        }
        
        return answer;
    }
    public static int dfs(Map<Integer,List<Integer>> map, boolean[] visited, int curr){
        int count = 1;
        visited[curr] = true;
        
        for (int next : map.get(curr)){
            if(!visited[next]){
                count += dfs(map,visited,next);
            }
        }
        return count;
    }
}