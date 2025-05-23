package week07.BOJ_2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[A+1][B+1][C+1];
        Set<Integer> set = new HashSet<>();

        // 초기 상태 : ( 0, 0, C )
        set.add(C);

        dfs(0, 0, C, A, B, C, visited, set);

        set.remove(0);
        StringBuilder sb = new StringBuilder();
        set.stream().sorted().forEach(i -> sb.append(i).append(" "));
        System.out.println(sb.toString().trim());

    }

    public static void dfs(int a, int b, int c, int A, int B, int C, boolean[][][] visited, Set<Integer> set){
        if(visited[a][b][c]) return;
        visited[a][b][c] = true;
        if (a == 0) {
            set.add(c);
        }

        // a -> b
        int result = check(A, B, a, b);
        if (result == 1) dfs(0, a + b, c, A, B, C, visited, set);
        else if (result == 2) dfs(a - (B - b), B, c, A, B, C, visited, set);

        // a -> c
        result = check(A, C, a, c);
        if (result == 1) dfs(0, b, a + c, A, B, C, visited, set);
        else if (result == 2) dfs(a - (C - c), b, C, A, B, C, visited, set);

        // b -> a
        result = check(B, A, b, a);
        if (result == 1) dfs(a + b, 0, c, A, B, C, visited, set);
        else if (result == 2) dfs(A, b - (A - a), c, A, B, C, visited, set);

        // b -> c
        result = check(B, C, b, c);
        if (result == 1) dfs(a, 0, b + c, A, B, C, visited, set);
        else if (result == 2) dfs(a, b - (C - c), C, A, B, C, visited, set);

        // c -> a
        result = check(C, A, c, a);
        if (result == 1) dfs(a + c, b, 0, A, B, C, visited, set);
        else if (result == 2) dfs(A, b, c - (A - a), A, B, C, visited, set);

        // c -> b
        result = check(C, B, c, b);
        if (result == 1) dfs(a, b + c, 0, A, B, C, visited, set);
        else if (result == 2) dfs(a, B, c - (B - b), A, B, C, visited, set);


    }

    public static int check(int X, int Y, int x, int y){
//        if (x == 0 || y == Y) return 0;
//        else if (Y >= x + y) return 1;  // x 물통을 다 비울 수 있을 때
//        else if (x > Y - y) return 2;  // y 물통이 가득 찰 때
//        return 0;  // 이동 불가

        if (x == 0 || y == Y) return 0;         // 이동 불가
        if (x + y <= Y) return 1;               // x 전부 옮길 수 있음
        return 2;
    }
}
