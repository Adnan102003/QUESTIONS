class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] st = new int[n + 1];
        int top = 0;
        st[top] = -1;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                st[++top] = i;
            } else {
                top--;
                if (top < 0) {
                    st[++top] = i;
                } else {
                    int len = i - st[top];
                    if (len > ans) ans = len;
                }
            }
        }
        return ans;
    }
}