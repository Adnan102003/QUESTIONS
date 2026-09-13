import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    a.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    b.add(new int[]{i, j});
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for (int[] p1 : a) {
            for (int[] p2 : b) {
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                int key = (dx + 100) * 1000 + (dy + 100);
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                if (count > ans) {
                    ans = count;
                }
            }
        }

        return ans;
    }
}