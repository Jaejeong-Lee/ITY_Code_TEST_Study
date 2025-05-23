package week07.BOJ_2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[A+1][B+1][C+1];
        Set<Integer> result = new HashSet<>();

        dfs(0, 0, C, A, B, C, visited, result);

        StringBuilder sb = new StringBuilder();
        result.stream().sorted().forEach(i -> sb.append(i).append(" "));
        System.out.println(sb.toString().trim());
    }

    public static void dfs(int a, int b, int c, int A, int B, int C, boolean[][][] visited, Set<Integer> result) {
        if (visited[a][b][c]) return;
        visited[a][b][c] = true;

        if (a == 0) result.add(c);

        // a -> b
        int move = pour(a, b, B);
        dfs(a - move, b + move, c, A, B, C, visited, result);

        // a -> c
        move = pour(a, c, C);
        dfs(a - move, b, c + move, A, B, C, visited, result);

        // b -> a
        move = pour(b, a, A);
        dfs(a + move, b - move, c, A, B, C, visited, result);

        // b -> c
        move = pour(b, c, C);
        dfs(a, b - move, c + move, A, B, C, visited, result);

        // c -> a
        move = pour(c, a, A);
        dfs(a + move, b, c - move, A, B, C, visited, result);

        // c -> b
        move = pour(c, b, B);
        dfs(a, b + move, c - move, A, B, C, visited, result);
    }

    public static int pour(int from, int to, int toCap) { // 옮길 물의 양 계산
        return Math.min(from, toCap - to);
    }
}
