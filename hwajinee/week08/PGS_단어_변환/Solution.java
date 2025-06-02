package week08.PGS_단어_변환;

import java.util.*;


public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
//        solution.solution(begin, target, words);
        System.out.println(solution.solution(begin, target, words));
    }

    class Node {
        String str;
        int depth;

        Node(String str, int depth){
            this.str = str;
            this.depth = depth;
        }
    }

    public int solution(String begin, String target, String[] words) {

        // bfs 를 돌기 전에 !!!
        // target이 words 배열에 없으면 바로 종료.
        boolean found = Arrays.asList(words).contains(target);
        if(!found) return 0;

        // bfs
        // 1. 필요한 자료구조 세팅
        // Queue에 저장할 정보 : 노드 번호 -> 현재 상태의 문자열, 변환 횟수(=depth)
        Deque<Node> queue = new ArrayDeque<>();
        // visited배열 : 특정 상태에 도달하면, 방문 기록을 체크한다.
        boolean[] visited = new boolean[words.length];

        // 2. 시작점 예약
        queue.add(new Node(begin, 0));

        // 3. 탐색 진행
        while(!queue.isEmpty()){
            Node curNode = queue.remove();
            // 현재 노드가 target인지
            if(curNode.str.equals(target)) return curNode.depth;

            // 다음 노드 고려
            List<Integer> nextWordsIdx = can_transform_words(curNode.str, words);
            if(nextWordsIdx.isEmpty()) continue;
            for(int i : nextWordsIdx){
//                System.out.println(curNode.str + "->" + words[i]);
                if(!visited[i]){
                    queue.add(new Node(words[i], curNode.depth + 1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }

    public List<Integer> can_transform_words(String str, String[] words){
        // 기준이 되는 문자열str이 들어오면, 문자열 배열 words 내에서
        // 하나의 알파벳만 다른 문자열을 찾고 그 인덱스를 리스트에 담아서 반환한다.
        List<Integer> can_transform_words = new ArrayList<>();

        for(int i=0; i<words.length; i++){ // words에 있는 단어 수만큼 반복
            int count = 0;
            for(int j=0; j<str.length(); j++){ // str 길이만큼 반복하며 한 문자씩 확인
                // str, words[i] 을 j를 기준으로 한 문자씩 검사하면서, 일치하지 않은 문자를 count한다.
                if(str.charAt(j) != words[i].charAt(j)){
                    count++;
                }
            }
            // count값이 1이면, can_transform_words배열에 담기
            if(count == 1){
                can_transform_words.add(i);
            }
        }
        return can_transform_words;
    }
}