package week07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjList_Dict {

    public static void printGraph(Map<Integer, List<Integer>> graph) {
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            Integer node = entry.getKey();
            List<Integer> neighbors = entry.getValue();

            System.out.print("노드 " + node + " -> ");
            for (Integer neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // 인접리스트(Dict)

        // 1. 인접 리스트 만들기
        Map<Integer, List<Integer>> adjList = new HashMap<>(){{
            put(0, List.of(1, 3, 6));
            put(1, List.of(0, 3));
            put(2, List.of(3));
            put(3, List.of(0, 1, 2, 7));
            put(4, List.of(5));
            put(5, List.of(4, 6, 7));
            put(6, List.of(0, 5));
            put(7, List.of(3, 5));
        }};
//        printGraph(adjList4);

        // 2. 간선 정보가 입력으로 주어질 때
        int n = 8; // 0부터 7까지 총 8개의 노드
        int[][] edges = {{0, 1}, {0, 3}, {0, 6}, {1, 3}, {2, 3}, {3, 7}, {4, 5}, {5, 6}, {5, 7}};

        Map<Integer, List<Integer>> adjList2 = new HashMap<>();
        for(int i=0; i<n; i++){
            adjList2.put(i, new ArrayList<>());
        }
        for(int i=0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];

            adjList2.get(a).add(b);
            adjList2.get(b).add(a); // 양방향 그래프
        }

        // 3. 인접 행렬로 주어질 때
        int[][] graph = {
                {0, 1, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0}
        };

        Map<Integer, List<Integer>> adjList3 = new HashMap<>();
        for(int i=0; i<n; i++){
            adjList3.put(i, new ArrayList<>());
            for(int j=0; j<n; j++){
                if(graph[i][j] == 1 && i!=j ){
                    adjList3.get(i).add(j);
                }
            }
        }
        printGraph(adjList3);

    }
}
