public class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int left = nums[i] - dp[j];
                int right = nums[j] - dp[j - 1];
                dp[j] = Math.max(left, right);
            }
        }
        return dp[n - 1] >= 0;
    }
}
