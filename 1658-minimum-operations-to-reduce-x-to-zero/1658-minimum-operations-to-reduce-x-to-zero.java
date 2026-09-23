class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }

        int t = sum - x;
        if (t < 0) return -1;
        if (t == 0) return nums.length;

        int n = nums.length;
        int l = 0;
        int cur = 0;
        int maxLen = -1;

        for (int r = 0; r < n; r++) {
            cur += nums[r];
            while (cur > t && l <= r) {
                cur -= nums[l];
                l++;
            }
            if (cur == t) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
}