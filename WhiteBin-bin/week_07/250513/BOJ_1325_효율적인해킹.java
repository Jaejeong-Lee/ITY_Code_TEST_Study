import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1325_효율적인해킹 {

    static ArrayList<Integer>[] array; // 인접 리스트 (B -> A 방향 저장)
    static int[] answer; // 해킹 가능한 수 저장

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        int m = Integer.parseInt(st.nextToken()); // 신뢰 관계 수

        array = new ArrayList[n + 1];
        answer = new int[n + 1];

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            array[i] = new ArrayList<>();
        }

        // 방향 그래프 만들기 (B -> A)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            array[B].add(A); // B를 해킹하면 A도 가능
        }

        // 모든 정점에서 BFS 실행
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            BFS(i, visited);
        }

        // 가장 해킹 가능한 수가 많은 값 찾기
        int max = Arrays.stream(answer).max().getAsInt();

        // 그 값을 가진 정점들 출력
        for (int i = 1; i <= n; i++) {
            if (answer[i] == max) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS(int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : array[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    answer[next]++; // next는 start로 인해 해킹될 수 있다
                    queue.add(next);
                }
            }
        }
    }
}
