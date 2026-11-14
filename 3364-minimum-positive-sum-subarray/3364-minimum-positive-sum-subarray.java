class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int best = Integer.MAX_VALUE;

        for (int len = l; len <= r; len++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += nums.get(i);
                if (i >= len) sum -= nums.get(i - len);
                if (i >= len - 1 && sum > 0 && sum < best) best = sum;
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}