public class LC_200_NumberofIslands {

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int rows, cols;
    char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        rows = grid.length;
        if(rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        int count = 0;
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < cols; y++) {
                if(grid[x][y] == '1') {
                    count++;
                    dfs(x, y);
                }
            }
        }
        return count;
    }

    public void dfs(int x, int y) {
        // 범위 체크 및 이미 방문했거나 바다('0')면 종료한다.
        if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0'; // 방문 처리 (방문한 땅을 바다로 바꿈)

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(nx, ny);
        }

    }
}