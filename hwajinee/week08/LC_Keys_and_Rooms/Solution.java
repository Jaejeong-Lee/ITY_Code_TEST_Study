package week08.LC_Keys_and_Rooms;

import java.util.*;

public class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;

        int curVertex = 0;
        dfs(rooms, visited, curVertex);

        // 방문하지 않은 노드가 존재하면, false 반환
        for(boolean check : visited){
            if(!check) return false;
        }
        return true;
    }

    public void dfs(List<List<Integer>> graph, boolean[] visited, int curVertex) {
        for(int nextVertex : graph.get(curVertex)){
            if(!visited[nextVertex]){
                visited[nextVertex] = true;
                dfs(graph, visited, nextVertex);
            }
        }
    }
}