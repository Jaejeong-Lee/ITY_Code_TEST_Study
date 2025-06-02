import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

 public class LC_785_IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        Queue<Integer> q = new LinkedList<>(); // BFS를 위한 큐
        HashMap<Integer, Character> map = new HashMap<>(); // 각 노드의 색을 저장 ('r' 또는 'b')

        // 모든 노드에 대해 검사 (그래프가 비연결 그래프일 수도 있기 때문에)
        for (int a = 0; a < graph.length; a++) {
            if (!map.containsKey(a)) { // 아직 방문하지 않은 노드라면
                q.add(a); // 큐에 추가
                char color = 'b'; // 시작 색상 설정

                while (!q.isEmpty()) {
                    int size = q.size();
                    // 현재 레벨의 색을 번갈아가며 설정 ('b' <-> 'r')
                    color = color == 'b' ? 'r' : 'b';

                    // 현재 레벨의 모든 노드를 처리
                    for (int i = 0; i < size; i++) {
                        int now = q.poll(); // 현재 노드

                        // 이미 색이 지정된 노드면 넘어감
                        if (map.containsKey(now)) continue;

                        // 현재 노드에 색 지정
                        map.put(now, color);

                        // 이웃 노드들 검사
                        for (int num : graph[now]) {
                            // 이미 색이 지정된 이웃 노드가 같은 색이라면 이분 그래프가 아님
                            if (map.getOrDefault(num, 'n') != 'n') {
                                if (map.get(num) == color) return false;
                            } else {
                                // 아직 색이 지정되지 않았다면 큐에 추가
                                q.add(num);
                            }
                        }
                    }
                }
            }
        }

        // 모든 노드를 검사했을 때 문제 없으면 이분 그래프임
        return true;
    }
}
