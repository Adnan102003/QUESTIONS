class Solution {
    public int maximumProduct(int[] nums) {
        int a = Integer.MIN_VALUE, b = Integer.MIN_VALUE, c = Integer.MIN_VALUE;
        int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n > a) {
                c = b;
                b = a;
                a = n;
            } else if (n > b) {
                c = b;
                b = n;
            } else if (n > c) {
                c = n;
            }

            if (n < x) {
                y = x;
                x = n;
            } else if (n < y) {
                y = n;
            }
        }

        return Math.max(a * b * c, x * y * a);
    }
}