Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:

grid[0][0]
an equal frequency of 'X' and 'Y'.
at least one 'X'.
 

Example 1:

Input: grid = [["X","Y","."],["Y",".","."]]

Output: 3

Explanation:



Example 2:

Input: grid = [["X","X"],["X","Y"]]

Output: 0

Explanation:

No submatrix has an equal frequency of 'X' and 'Y'.

Example 3:

Input: grid = [[".","."],[".","."]]

Output: 0

Explanation:

No submatrix has at least one 'X'.

 

Constraints:

1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'.


SOLUTION:::
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int r = grid.length, c = grid[0].length, ans = 0;
        int[][] x = new int[r + 1][c + 1], y = new int[r + 1][c + 1];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                x[i + 1][j + 1] = x[i][j + 1] + x[i + 1][j] - x[i][j] + (grid[i][j] == 'X' ? 1 : 0);
                y[i + 1][j + 1] = y[i][j + 1] + y[i + 1][j] - y[i][j] + (grid[i][j] == 'Y' ? 1 : 0);
                if (x[i + 1][j + 1] == y[i + 1][j + 1] && x[i + 1][j + 1] > 0) ans++;
            }
        }
        return ans;
    }
}
