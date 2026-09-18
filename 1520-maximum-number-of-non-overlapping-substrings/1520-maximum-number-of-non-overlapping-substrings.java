import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] l = new int[26];
        int[] r = new int[26];
        Arrays.fill(l, -1);
        Arrays.fill(r, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (l[c] == -1) l[c] = i;
            r[c] = i;
        }

        List<int[]> segs = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (l[i] == -1) continue;

            int left = l[i];
            int right = r[i];
            boolean ok = true;

            for (int j = left; j <= right; j++) {
                int c = s.charAt(j) - 'a';
                if (l[c] < left) {
                    ok = false;
                    break;
                }
                right = Math.max(right, r[c]);
            }

            if (ok) {
                segs.add(new int[]{left, right});
            }
        }

        Collections.sort(segs, (a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();
        int last = -1;

        for (int[] seg : segs) {
            if (seg[0] > last) {
                ans.add(s.substring(seg[0], seg[1] + 1));
                last = seg[1];
            }
        }

        return ans;
    }
}