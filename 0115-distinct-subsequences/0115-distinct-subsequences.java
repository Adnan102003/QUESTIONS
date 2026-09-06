class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (m < n) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            char a = s.charAt(i - 1);
            for (int j = n; j >= 1; j--) {
                char b = t.charAt(j - 1);
                if (a == b) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}