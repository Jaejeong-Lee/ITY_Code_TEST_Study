package week07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjList_List {

    // List 출력
    public static void printGraph(List<List<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("node " + i + " -> ");
            for (int neighbor : graph.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Map 출력
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

        // 인접리스트(List)

        // 1. 인접 리스트 만들기
        /*
            참고로, {{ add(List.of ...) }} 에서
           첫 번째 { 는 익명 클래스 선언부, 두 번째 { 는 인스턴스 초기화 블록을 의미한다.
        */
        List<List<Integer>> adjList = new ArrayList<>() {{
            add(List.of(1, 3, 6));
            add(List.of(0, 3));
            add(List.of(3));
            add(List.of(0, 1, 2, 7));
            add(List.of(5));
            add(List.of(4, 6, 7));
            add(List.of(0, 5));
            add(List.of(3, 5));
        }};
//        printGraph(adjList);

        // 2. 간선 정보가 입력으로 주어질 때, 인접리스트로 변환하기
        int n = 8; // 0부터 7까지 총 8개의 노드
        int[][] edges = {{0, 1}, {0, 3}, {0, 6}, {1, 3}, {2, 3}, {3, 7}, {4, 5}, {5, 6}, {5, 7}};

        List<List<Integer>> adjList2 = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList2.add(i, new ArrayList<>()); // 노드 번호, 연결된 노드 리스트
        }
        for(int i=0; i< edges.length; i++){
            int a = edges[i][0]; // 출발 노드 번호
            int b = edges[i][1]; // 도착 노드 번호

            adjList2.get(a).add(b);
            adjList2.get(b).add(a); // 무방향(양방향) 그래프일 때
        }
//        printGraph(adjList2);

        // 3. 인접 행렬로 주어질 때, 인접 리스트로 변환
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

        int N = graph.length;
        List<List<Integer>> adjList3 = new ArrayList<>();
        for(int i=0; i<N; i++){
            adjList3.add(i, new ArrayList<>());
            for(int j=0; j<N; j++){
                if(graph[i][j] == 1 && i != j) {
                    adjList3.get(i).add(j);
                }
            }
        }
//        printGraph(adjList3);

    }
}
