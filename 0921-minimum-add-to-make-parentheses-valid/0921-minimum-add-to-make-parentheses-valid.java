class Solution {
    public int minAddToMakeValid(String s) {
        int o = 0, ans = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                o++;
            } else {
                if (o > 0) {
                    o--;
                } else {
                    ans++;
                }
            }
        }

        return ans + o;
    }
}