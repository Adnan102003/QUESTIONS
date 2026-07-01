class Solution {

    int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] a = new int[n][n];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.add(new int[]{i, j});
                    a[i][j] = 0;
                } else {
                    a[i][j] = -1;
                }
            }
        }

        while (!q.isEmpty()) {
            int s = q.size();
            while (s-- > 0) {
                int[] c = q.poll();
                for (int[] x : d) {
                    int ni = c[0] + x[0];
                    int nj = c[1] + x[1];

                    if (ok(a, ni, nj) && a[ni][nj] == -1) {
                        a[ni][nj] = a[c[0]][c[1]] + 1;
                        q.add(new int[]{ni, nj});
                    }
                }
            }
        }

        int l = 0, r = 0, ans = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r = Math.max(r, a[i][j]);
            }
        }

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (check(a, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return ans;
    }

    boolean check(int[][] a, int x) {
        int n = a.length;

        if (a[0][0] < x || a[n - 1][n - 1] < x) {
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[n][n];

        q.add(new int[]{0, 0});
        v[0][0] = true;

        while (!q.isEmpty()) {
            int[] c = q.poll();

            if (c[0] == n - 1 && c[1] == n - 1) {
                return true;
            }

            for (int[] k : d) {
                int ni = c[0] + k[0];
                int nj = c[1] + k[1];

                if (ok(a, ni, nj) && !v[ni][nj] && a[ni][nj] >= x) {
                    v[ni][nj] = true;
                    q.add(new int[]{ni, nj});
                }
            }
        }

        return false;
    }

    boolean ok(int[][] a, int i, int j) {
        int n = a.length;
        return i >= 0 && j >= 0 && i < n && j < n;
    }
}