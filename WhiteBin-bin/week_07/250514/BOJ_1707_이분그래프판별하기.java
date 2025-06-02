import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프판별하기 {
    static ArrayList<Integer>[] array;
    static int[] check;
    static boolean visited[];
    static boolean IsEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            array = new ArrayList[v + 1];
            visited = new boolean[v + 1];
            check = new int[v + 1];
            IsEven = true;

            for (int j = 1; j <= v; j++) {
                array[j] = new ArrayList<Integer>();
            }

            for (int k = 0; k < w; k++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                array[start].add(end);
                array[end].add(start);
            }

            // 모든 노드에 대해 확인 (연결되지 않은 그래프가 있을 수 있음)
            for (int l = 1; l <= v; l++) {
                if (!visited[l]) {  // 방문하지 않은 노드에 대해서만 DFS 수행
                    DFS(l);
                    if (!IsEven) {
                        break;  // 이분 그래프가 아니면 더 이상 확인할 필요 없음
                    }
                }
            }

            // 모든 노드를 확인한 후 결과 출력
            if (IsEven) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int node) {
        visited[node] = true;
        for (int i : array[node]) {
            if (!visited[i]) {
                // 인접한 노드는 같은 집합이 아니므로 이전 노드와 다른 집합으로 처리
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            } else if (check[node] == check[i]) {
                IsEven = false;
                return;  // 이분 그래프가 아니면 즉시 종료
            }
        }
    }
}