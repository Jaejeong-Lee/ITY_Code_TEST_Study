package week_07;

import java.io.*;
import java.util.*;

public class BOJ18352 {
    public static void main(String[] args) throws IOException {
        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 도시 수
        int m = Integer.parseInt(st.nextToken()); // 도로 개수
        int k = Integer.parseInt(st.nextToken()); // 최단 거리
        int x = Integer.parseInt(st.nextToken()); // 시작점 도시

        // 인접 리스트 생성
        // Map에 {키 : 목적지, 값 : 최단 거리} 추가하면서 진행되어야 하므로
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int tempStart = Integer.parseInt(st.nextToken());
            int tempEnd = Integer.parseInt(st.nextToken());
            map.get(tempStart).add(tempEnd);
        }
        /* 인접리스트 생성 결과 1 : [ 1: [2, 3], 2: [3, 4], 3: [], 4: []] */
            /* 인접리스트 생성 결과 2 : [ 1: [2, 3, 4], 2; [], 3: [], 4: []] */

        // bfs 사용 -> 최단 거리가 K인 모든 도시들 배열 계산
        ArrayList<Integer> answer = bfs(x, n, k, map);

        /* 해당 도시들 출력 */
        // 도시 번호들 오름차순 정렬
        // 우선순위 큐 사용할 수도 있음.
        Collections.sort(answer);

        for ( Integer i : answer) {
            System.out.println(i);
        }
    }
    private static ArrayList<Integer> bfs(int x, int n, int k, Map<Integer,List<Integer>> map) {
        // 초기값 세팅
        // n+1이 아닌 n까지만 하면 인덱스 오류 발생
        boolean[] visited = new boolean[n+1];

        // answer 인 도시 모아놓은 배열 생성
        // answer을 직접 return함
        ArrayList<Integer> answer = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            // 현재 도시(노드)부터 검사 - for문
            // queue에서 poll하기
            int[] curr = queue.poll();
            int currCity = curr[0];
            int dist = curr[1];

            // 만약 최단거리가 k를 넘어가면 continue
            if (dist > k) continue;

            // 최단거리에 부합하는 도시 판단 후 저장하기.
            // for문 전에 코드 넣음으로써 불필요한 연산 방지
            if (dist == k) {
                answer.add(currCity);
                // continue;
            }

            // 다음 노드 방문 예약
            for (int next : map.get(currCity)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, dist+1});
                }
            }
        }
        if (answer.isEmpty()) {
            answer.add(-1);
        }
        return answer;
    }
}
