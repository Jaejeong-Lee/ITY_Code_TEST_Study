package week_07;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 효율적인 해킹
public class BOJ_1325 {
    static int N, M;
    static boolean visited[]; // 방문여부를 저장할 배열 visited
    static int answer[];
    static ArrayList<Integer> A[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        M = Integer.parseInt(st.nextToken()); // 신뢰 관계 개수
        A = new ArrayList[N + 1]; // 1부터 시작이니 N+1, 인접리스트
        answer = new int[N + 1]; // 답도
        for (int i = 0; i <= N; i++) {
            A[i] = new ArrayList<>(); // 인접리스트
        }
        for (int i = 0; i < M; i++) { // 인접리스트에 관계 데이터 그래프 표현
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1]; // 방문 여부를 저장할 배열 새로 초기화
            BFS(i); // BFS 실행
        }
        int maxVal = 0;
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, answer[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (answer[i] == maxVal) {
                System.out.print(i+" ");
            }
        }
    }

    // BFS 메서드
    public static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            int now_Node = queue.poll();
            for (int i : A[now_Node]) {
                if (visited[i] == false) {
                    visited[i] = true;
                    answer[i]++;
                    queue.add(i);
                }
            }
        }
    }
}
