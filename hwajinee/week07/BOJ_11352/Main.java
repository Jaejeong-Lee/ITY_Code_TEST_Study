package week07.BOJ_11352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N: 노드 개수
        int M = Integer.parseInt(st.nextToken()); // M: 도로 개수
        int K = Integer.parseInt(st.nextToken()); // K: 최단거리
        int X = Integer.parseInt(st.nextToken()); // X: 출발 노드

        // graph 인접리스트 만들기
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<N; i++){
            graph.put(i+1, new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        // bfs
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        // 최단거리별 노드번호를 저장할 리스트
        List<List<Integer>> answer = new ArrayList<>();
        for(int distance=0; distance<=M; distance++){ // 최대 거리는 도로의 개수인 M
            answer.add(distance,new ArrayList<>());
        }

        queue.add(new int[]{X, 0});
        visited[X-1] = true;
        // 최단거리가 0인 1번 노드 기록
        answer.get(0).add(1);

        while(!queue.isEmpty()){
            int[] curVertex = queue.pop();
            for(int nextVertex : graph.get(curVertex[0])) {
                if(!visited[nextVertex-1]) {
                    queue.add(new int[]{nextVertex, curVertex[1] + 1});
                    visited[nextVertex-1] = true;
                    // 최단거리가 curVertex[1] + 1인 nextVertex번 노드 기록
                    answer.get(curVertex[1] + 1).add(nextVertex);
                }
            }
        }

        // 최단거리가 K인 노드정보가 담긴 리스트를 오름차순 정렬 후 출력한다.
        if(answer.get(K).isEmpty()){
            System.out.println(-1);
        } else {
            List<Integer> result = answer.get(K);
            Collections.sort(result);
            for(int n : result)
            System.out.println(n);
        }

    }
}
