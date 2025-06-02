package week07.BOJ_1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// bfs로 구현하였을 때, 시간초과 발생하여 dfs로 수정했고 통과함 !!!
/*
    [BOJ-1325] 효율적인 해킹

        "A가 B를 신뢰한다" == B -> A 간선이 존재한다.

        그래프에서 연결된 노드의 개수가 가장 많은 특정 노드를 찾는 문제.

        그래프의 모든 노드에서 DFS 순회를 하고, 이 때 방문한 노드의 수를 count한다.
        노드의 갯수를 크기로 하는 int배열을 만들고, 여기에 각 노드에 대한 count값을 저장한다.

        가장 큰 count값을 가지는 노드번호를 추출하고, 이를 오름차순 정렬하여 출력한다.
 */
public class Main_DFS {
    public static int count = 0;
    public static int dfs(boolean[] visited, int curVertex, List<List<Integer>> graph){
        visited[curVertex] = true;

        for(int nextVertex : graph.get(curVertex)){
            if(!visited[nextVertex]){
                dfs(visited, nextVertex, graph);
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph.get(b).add(a);
        }

        // dfs
        int[] network = new int[N+1]; // 노드별 연결된 네트워크 크기 저장.

        for (int i = 0; i < N; i++) { // i: bfs 시작 노드
            boolean[] visited = new boolean[N];
            network[i] = dfs(visited,i, graph); // return받은 네트워크 크기 저장.
            count =0;
        }

        // network에는 노드별 최대 해킹 가능한 컴퓨터 수가 저장되어 있음.
        int max = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (network[i] > max) {
                // result 초기화
                result.clear();
                result.add(i + 1); // 노드번호가 1부터 시작함
                max = network[i];
            } else if (network[i] == max) {
                result.add(i + 1);
            }
        }

        Arrays.sort(result.toArray());
        for (int n : result) {
            System.out.print(n + " ");
        }
    }
}

