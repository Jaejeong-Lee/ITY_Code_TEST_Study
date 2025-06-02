package week_08;

import java.util.*;

public class PGS_단어변환 {
    public int solution(String begin, String target, String[] words) {
        // 애초에 target이 words에 없으면 return 0
        Set<String> set = new HashSet<>(Arrays.asList(words));
        if (!set.contains(target)) return 0;

        // 인접 리스트 생성
        Map<String, List<String>> map = new HashMap<>();
        // begin은 words에 없으므로 인접리스트 생성시 추가해줌
        for (String w : words) {
            map.put(w, new ArrayList<>());
        }
        map.put(begin, new ArrayList<>());

        //begin의 인접 리스트 생성
        for (String word : words) {
            if (isOneDiff(word, begin)) {
                map.get(begin).add(word);
                map.get(word).add(begin);
            }
        }

        for (String word : words) {
            for (String w : words) {
                // 인접 리스트 생성
                // 한 글자만 다르면 인접 리스트에 추가
                // addList() : 1글자만 다를 때 true 반환, 나머지 경우 false
                if (isOneDiff(word, w)) {
                    map.get(word).add(w);
                    map.get(w).add(word);
                }
            }
        }
        // BFS
        // 초깃값 세팅
        Queue<Map.Entry<String, Integer>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        queue.offer(new AbstractMap.SimpleEntry<>(begin, 0));

        while (!queue.isEmpty()) {
            //현재 노드 방문 : 값 꺼내기
            Map.Entry<String, Integer> temp = queue.poll();
            Integer time = temp.getValue();
            String curr = temp.getKey();

            if (curr.equals(target)) return time;

            for (String next : map.get(curr)) {
                if (!visited.contains(next)) {
                    // 다음 노드 방문
                    visited.add(next);
                    queue.offer(new AbstractMap.SimpleEntry<>(next, time + 1));
                }
            }
        }
        return 0;
    }

    public static boolean isOneDiff(String word, String w) {
        String[] temp = word.split("");
        String[] tempW = w.split("");
        // 같은 글자면 체크 안 하고 continue
        if (word.equals(w)) return false;

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (temp[i].equals(tempW[i])) count++;
        }
        // 인접 리스트 생성
        // 한 글자만 다르면 인접 리스트에 추가
        // true 반환
        if (count == word.length() - 1) return true;

        return false;
    }
}