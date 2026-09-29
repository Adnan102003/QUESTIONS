class Solution {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int total = n + m - 1;

        if (total % 2 != 0 || grid[0][0] != '(' || grid[n - 1][m - 1] != ')') {
            return false;
        }

        boolean[][][] visited = new boolean[n][m][total / 2 + 1];
        return dfs(grid, 0, 0, 0, visited, total / 2);
    }

    private boolean dfs(char[][] g, int r, int c, int bal, boolean[][][] vis, int maxOpen) {
        bal += (g[r][c] == '(' ? 1 : -1);

        if (bal < 0 || bal > maxOpen || vis[r][c][bal]) {
            return false;
        }
        vis[r][c][bal] = true;

        if (r == g.length - 1 && c == g[0].length - 1) {
            return bal == 0;
        }

        if (r + 1 < g.length && dfs(g, r + 1, c, bal, vis, maxOpen)) {
            return true;
        }
        if (c + 1 < g[0].length && dfs(g, r, c + 1, bal, vis, maxOpen)) {
            return true;
        }

        return false;
    }
}