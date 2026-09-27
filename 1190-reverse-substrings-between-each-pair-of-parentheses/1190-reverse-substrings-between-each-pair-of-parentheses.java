import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> st = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else if (c == ')') {
                int j = st.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int d = 1;

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(c);
            }
            i += d;
        }

        return sb.toString();
    }
}