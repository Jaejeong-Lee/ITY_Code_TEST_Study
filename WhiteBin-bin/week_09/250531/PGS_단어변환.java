import java.util.*;

public class PGS_단어변환 {

    // 단어 + 지금까지 몇 번 변환했는지를 담는 클래스
    static class Word {
        String word;
        int step;

        Word(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public int solution(String begin, String target, String[] words) {
        // target이 단어 목록에 없으면 변환 불가
        if (!Arrays.asList(words).contains(target)) return 0;

        Queue<Word> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.offer(new Word(begin, 0));  // 시작 단어와 변환 횟수 0부터 시작

        while (!queue.isEmpty()) {
            Word current = queue.poll();

            // 목표 단어에 도달하면 변환 횟수 리턴
            if (current.word.equals(target)) {
                return current.step;
            }

            // words 배열에서 한 글자만 다른 단어들을 찾아서 큐에 추가
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && isOneLetterDiff(current.word, words[i])) {
                    visited[i] = true;
                    queue.offer(new Word(words[i], current.step + 1));
                }
            }
        }

        return 0;  // 변환 불가능한 경우
    }

    // 두 단어가 한 글자만 다른지 확인하는 함수
    private boolean isOneLetterDiff(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}
