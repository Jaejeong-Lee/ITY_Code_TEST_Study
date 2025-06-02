import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352_특정거리도시찾기 {

    public static void main(String[] args) throws IOException {
        int visited[]; // 거리 저장용
        ArrayList<Integer>[] array;
        int n, m, k, x;
        List<Integer> answer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x =  Integer.parseInt(st.nextToken());

        array = new ArrayList[n + 1];
        visited = new int[n + 1];
        answer = new ArrayList<>();

        // 배열에 각 칸에 실제 리스트를 만드는 부분
        for(int i = 0; i <= n; i++) {
            array[i] = new ArrayList<>();
            visited[i] = -1;  // 방문하지 않음을 -1로 표시
        }

        // 간선 입력 받기
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            array[from].add(to); // 단방향 그래프
        }

        // BFS 시작
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = 0; // 자기 자신은 거리 0

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : array[current]) {
                if(visited[next] == -1) {
                    visited[next] = visited[current] + 1;
                    queue.offer(next);
                }
            }
        }

        // 거리 k인 도시 찾기
        for(int i = 1; i <= n; i++) {
            if(visited[i] == k) {
                answer.add(i);
            }
        }

        if(answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for(int city : answer) {
                System.out.println(city);
            }
        }
    }
}
