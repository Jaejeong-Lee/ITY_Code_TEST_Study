package week_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 이분그래프
public class BOJ_1707 {
    static ArrayList<Integer>[] A; // 그래프 데이터 저장 인접 리스트
    static int[] check; // 이분 그래프 체크 배열
    static boolean visited[]; // 방문 기록 저장 배열
    static boolean IsEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            String[] s = br.readLine().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            A = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            check = new int[V + 1];
            IsEven = true;
            for (int i = 0; i < V; i++) { // 인접 리스트의 각 ArrayList 초기화
                A[i] = new ArrayList<>();
            }
            for (int i = 0; i < E; i++) { // 인접 리스트에 그래프 데이터 저장
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                A[start].add(end);
                A[end].add(start);
            }

            // DFS 실행
            for (int i = 1; i <= V; i++) {
                if (IsEven) {
                    DFS(i);
                } else { // 만약 이분그래프가 아니면 종료
                    break;
                }
            }
            if (IsEven) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    } // end of main method()

    public static void DFS(int node) {
        visited[node] = true;
        for (int i : A[node]) {
            if (!visited[i]) {
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            } else if (check[node] == check[i]) {
                IsEven = false;
            }
        }
    } // end of DFS method()
}
