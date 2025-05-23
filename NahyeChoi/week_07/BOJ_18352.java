package week_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// boj 18352 : 특정 거리의 도시 찾기
// 모든 도로의 거리가 1이니 가중치가 없는 인접 리스트 사용
public class BOJ_18352 {
    // 전역 변수 선언
    static int N, M, K, X;
    static int visited[]; //출발 도시로부터 i번 도시까지의 거리
    static ArrayList<Integer>[] A; // 인접리스트 그래프
    static List<Integer> answer; // 정답 저장용 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력 : // N(노드개수), M(에지개수), K(목표 거리), X(시작점)
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        // 정답 리스트 초기화 : answer
        answer = new ArrayList<>();
        // 인접 리스트 초기화 (도시는 1번부터 시작하므로 N+1 크기로 생성한다.)
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }

        // 도로 정보 입력 받은 뒤 인접 리스트에 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); //출발도시
            int E = Integer.parseInt(st.nextToken()); //도착도시
            A[S].add(E); // S->E 도로 추가(단방향)
        }

        // visited 방문 거리 저장 배열 (-1은 아직 방문하지 않았다는 것)
        visited = new int[N + 1]; // 방문 배열 초기화
        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
        }

        BFS(X);
        // 모든 도시 중에서 출발 도시로부터의 거리가 K인 도시를 정답 리스트에 추가
        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }
        // 정답 출력
        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for (int temp : answer) {
                System.out.println(temp);
            }
        }
    }

    // BFS 구현
    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node); //출발 도시 큐에 넣기
        visited[Node]++;
        while (!queue.isEmpty()) {
            int now_Node = queue.poll(); //현재 도시 꺼내기
            // 현재 도시에서 이동할 수 있는 모든 도시 출력
            for (int i : A[now_Node]) {
                // 아직 방문하지 않은 도시라면
                if (visited[i] == -1) {
                    // 현재 도시까지 거리 + 1
                    visited[i] = visited[now_Node] + 1;
                    queue.add(i); // 다음 도시 큐에 추가
                }
            }
        }
    }
}
