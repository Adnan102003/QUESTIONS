class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] dp = new long[k];
        long[] next = new long[k];

        for (int x : nums) {
            int m = x % k;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    next[(int) (((long) r * m) % k)] += dp[r];
                }
            }
            next[m]++;
            for (int r = 0; r < k; r++) {
                dp[r] = next[r];
                res[r] += next[r];
                next[r] = 0;
            }
        }

        return res;
    }
}