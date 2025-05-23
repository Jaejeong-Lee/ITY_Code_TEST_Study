package week_07;

import java.util.*;

public class BOJ_특정거리의도시찾기 {
    static int visited[];
    static ArrayList<Integer>[] A;
    static int N, M, K, X;
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt(); // 노드의 수
        M = scan.nextInt(); // 에지의 수
        K = scan.nextInt(); // 목표 거리
        X = scan.nextInt(); // 시작점
        A = new ArrayList[N + 1];
        answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            int S = scan.nextInt();
            int E = scan.nextInt();
            A[S].add(E);
        }

        visited = new int[N + 1]; // 방문 배열 초기화하기
        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
        }

        BFS(X);

        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for (int city : answer) {
                System.out.println(city);
            }
        }
    }

    public static void BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : A[now]) {
                if (visited[i] == -1) {
                    visited[i] = visited[now] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
