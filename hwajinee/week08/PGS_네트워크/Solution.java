package week08.PGS_네트워크;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private static int count = 0;

    public int solution(int n, int[][] computers) {
        // 인접 행렬 computers -> 인접 리스트 adjList로 변환
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i <n; i++) {
            graph.put(i, new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j) {
                    graph.get(i).add(j);
                }
            }
        }
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]) {
                dfs(graph, visited, i);
                count ++;
            }
        }
        return count;
    }

    public void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int curVertex){
        visited[curVertex] = true;
        for(int nextVertex : graph.get(curVertex)){
            if(!visited[nextVertex]) {
                dfs(graph, visited, nextVertex);
            }
        }
    }

}