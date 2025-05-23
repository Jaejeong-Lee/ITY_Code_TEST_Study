package week_07;

import java.io.*;
import java.util.*;

public class BOJ_이분그래프 {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean[] visited;
    static boolean IsEven;

    public static void main(String[] args) throws Exception {
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

            for (int i = 1; i <= V; i++) {
                A[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                s = br.readLine().split(" ");
                int Start = Integer.parseInt(s[0]);
                int End = Integer.parseInt(s[1]);
                A[Start].add(End);
                A[End].add(Start);
            }

            // 연결 요소가 여러 개일 수 있으므로 모든 정점에 대해 수행
            for (int i = 1; i <= V; i++) {
                if (IsEven) {
                    if (!visited[i]) {
                        DFS(i);
                    }
                } else {
                    break;
                }
            }

            if (IsEven) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

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
    }
}
