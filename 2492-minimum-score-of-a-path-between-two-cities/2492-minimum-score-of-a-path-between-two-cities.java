import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    class Pair {
        int node;
        int dist;
        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int minScore(int n, int[][] roads) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int d = r[2];
            adj.get(u).add(new Pair(v, d));
            adj.get(v).add(new Pair(u, d));
        }

        boolean[] vis = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        
        q.add(1);
        vis[1] = true;
        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (Pair p : adj.get(curr)) {
                ans = Math.min(ans, p.dist);
                if (!vis[p.node]) {
                    vis[p.node] = true;
                    q.add(p.node);
                }
            }
        }

        return ans;
    }
}