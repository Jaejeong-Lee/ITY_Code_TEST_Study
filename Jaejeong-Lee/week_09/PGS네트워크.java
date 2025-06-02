package week_09;

/* DFS 문제 */
public class PGS네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers, visited, i);
                // dfs 다 돌고 나서
                // 즉, 연결된 거 다 돌고 와서
                // answer 하나 추가해줌.
                answer++;
            }
        }
        return answer;
    }
    private void dfs(int[][] computers, boolean[] visited, int current) {
        // 현재 노드 방문표시
        visited[current] = true;

        for (int i = 0; i < computers.length; i++) {
            // 현재 컴퓨터와 다른 컴퓨터가 연결 되어있고, 방문하지 않았다면
            // 방문 여부 조건 덕분에 computers의 대각선 반대쪽 내용 겹치는 부분 탐색 안하게 됨
            if (computers[current][i] == 1 && !visited[i]) {
                dfs(computers, visited, i);
            }
        }
    }
}