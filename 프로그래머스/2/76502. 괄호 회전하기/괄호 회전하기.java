import java.util.*;

class Solution {
public int solution(String s) {
        int answer = 0;

        int x = s.length();
        List<Character> input = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            input.add(s.charAt(i));
        }
        int count = 0;
        while (count < x) {
            // 회전
            if (count != 0) {
                char first = input.remove(0);
                input.add(first);
            }

            // 올바른 괄호인지 확인
            // 1. check 스택에 괄호 닫는 괄호로 바꾸어 넣기
            // 2. 닫는 괄호인 경우, deque 뒤에서 꺼내서
            // 3. 같으면 answer 증가, 다르면 break.
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;

            for (int i = 0; i < x; i++) {
                Character curr = input.get(i);
                // 여는 괄호면 닫는 괄호로 바꿔서
                // deque에 넣음
                if (curr == '[' || curr == '(' || curr == '{') {
                    stack.push(curr);
                } else {
                    ;
                    // 닫는 괄호면 뒤에서 꺼내면서 같은지 비교
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    }

                    char closed = stack.pop();
                    if ((curr == ']' && closed != '[') ||
                            (curr == '}' && closed != '{') ||
                            (curr == ')' && closed != '(')) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid && stack.isEmpty()) answer++;
            count++;
        }
        return answer;
    }
}

