You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.

 

Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 105


SOLUTION::::
import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] data) {
        int h = data.length;
        int w = data[0].length;
        TreeSet<Integer> bestSums = new TreeSet<>();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                updateTopThree(bestSums, data[r][c]);
                for (int len = 1; ; len++) {
                    int tr = r, tc = c;
                    int br = r + 2 * len, bc = c;
                    int lr = r + len, lc = c - len;
                    int rr = r + len, rc = c + len;
                    if (br >= h || lc < 0 || rc >= w) break;
                    int total = data[tr][tc] + data[br][bc] + data[lr][lc] + data[rr][rc];
                    for (int k = 1; k < len; k++) {
                        total += data[tr + k][tc - k];
                        total += data[tr + k][tc + k];
                        total += data[br - k][bc - k];
                        total += data[br - k][bc + k];
                    }
                    updateTopThree(bestSums, total);
                }
            }
        }
        int[] out = new int[bestSums.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = bestSums.pollLast();
        }
        return out;
    }
    private void updateTopThree(TreeSet<Integer> container, int val) {
        container.add(val);
        if (container.size() > 3) {
            container.pollFirst();
        }
    }
}
