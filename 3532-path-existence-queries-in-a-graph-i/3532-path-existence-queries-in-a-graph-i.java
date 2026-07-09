class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] id = new int[n];
        int curr = 0;
        id[0] = curr;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                curr++;
            }
            id[i] = curr;
        }
        
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = (id[u] == id[v]);
        }
        
        return ans;
    }
}