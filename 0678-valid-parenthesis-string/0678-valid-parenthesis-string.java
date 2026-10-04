class Solution {
    public boolean checkValidString(String s) {
        int l = 0, r = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else {
                l--;
                r++;
            }

            if (r < 0) return false;
            if (l < 0) l = 0;
        }

        return l == 0;
    }
}