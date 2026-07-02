class Solution {

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(d[i], -1);
        }

        int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{grid.get(0).get(0), 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int cost = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (d[x][y] != -1) {
                continue;
            }

            d[x][y] = cost;

            for (int[] k : dir) {
                int nx = x + k[0];
                int ny = y + k[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                if (d[nx][ny] != -1) {
                    continue;
                }

                pq.offer(new int[]{cost + grid.get(nx).get(ny), nx, ny});
            }
        }

        return d[m - 1][n - 1] < health;
    }
}