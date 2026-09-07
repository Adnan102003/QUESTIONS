class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] last = new int[26];
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            int added = (total + 1 - last[c]) % MOD;
            if (added < 0) {
                added += MOD;
            }

            total = (total + added) % MOD;
            last[c] = (last[c] + added) % MOD;
        }

        return total;
    }
}