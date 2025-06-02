import java.util.*;

public class PGS_거리두기확인하기 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int idx = 0; idx < places.length; idx++) {
            String[] place = places[idx];
            boolean isValid = true;

            for (int i = 0; i < 5 && isValid; i++) {
                for (int j = 0; j < 5 && isValid; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!bfs(place, i, j)) {
                            isValid = false;
                        }
                    }
                }
            }

            answer[idx] = isValid ? 1 : 0;
        }

        return answer;
    }

    private boolean bfs(String[] place, int x, int y) {
        boolean[][] visited = new boolean[5][5];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y, 0));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.dist >= 1 && place[node.x].charAt(node.y) == 'P') {
                return false;
            }

            if (node.dist == 2) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nx = node.x + dx[dir];
                int ny = node.y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (place[nx].charAt(ny) == 'X') continue;

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, node.dist + 1));
            }
        }

        return true;
    }

    static class Node {
        int x, y, dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
