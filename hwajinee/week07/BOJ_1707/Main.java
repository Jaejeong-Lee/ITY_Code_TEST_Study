package week07.BOJ_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    [BOJ-1707] 이분 그래프

    그래프의 정점의 집합을 둘로 나눴을 때, 각 집합에 속한 정점끼리 연결된 간선이 없을 때 이분그래프가 된다.
    즉, 하나의 간선에 대해 시작정점과 도착정점을 다른 색으로 칠했을 때,
    전체 그래프에서 두 가지 색으로만 색칠할 수 있으면 이분 그래프가 된다.

    풀이 방법 ) dfs 그래프를 순회하며 두 가지 색 중 하나로 정점을 색칠하는데,
    자신을 칠하는 색과 자신과 연결된 다음 정점을 칠하는 색을 다르게 한다.
    이 때, 자신과 연결된 다른 정점의 색이 자신과 동일한 경우가 하나라도 존재하면 이분 그래프가 아니고,
    그래프 전체를 순회했는데 그런 경우가 나오지 않으면 이분그래프이다.

    그래프를 색칠하는 방법은 노드의 갯수만큼 int형 배열을 생성하고,
    방문하지 않은 노드는 0, 방문한 노드는 1 또는 2로 색칠한다.

 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringTokenizer st;
        List<List<Integer>> graph = new ArrayList<>();
        StringBuilder outputStr = new StringBuilder();

        for(int n=0; n<t; n++){ // 테스트 케이스만큼 반복
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // 정점의 개수
            int e = Integer.parseInt(st.nextToken()); // 간선의 개수

            for(int j=0; j<v; j++){
                graph.add(j, new ArrayList<>());
            }

            for(int j=0; j<e; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            // bfs
            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[v];
            int[] color = new int[v]; // 1 또는 2로 색칠
            boolean isRight = true;

            for (int i = 0; i < v; i++) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    color[i] = 1;

                    while(!queue.isEmpty()){
                        int curVertex = queue.poll();
                        for(int nextVertex : graph.get(curVertex)){
                            // 이분그래프 여부 확인
                            if(color[nextVertex] == color[curVertex]){
                                isRight = false;
                                break;
                            } else if(!visited[nextVertex]){
                                visited[nextVertex] = true;
                                color[nextVertex] = 3- color[curVertex]; // 2 <-> 1
                                queue.add(nextVertex);
                            }
                        }
                    }
                }
            }
            outputStr.append(isRight ? "YES\n" : "NO\n");
            graph.clear();
        }
        System.out.println(outputStr.toString());
    }
}
