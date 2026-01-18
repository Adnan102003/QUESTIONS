A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.

 

Example 1:


Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
Example 2:


Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 106


SOLUTION:::
class Solution {
    private int[][] rowSum;
    private int[][] colSum;

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        rowSum = new int[m + 1][n + 1];
        colSum = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rowSum[i][j] = rowSum[i][j - 1] + grid[i - 1][j - 1];
                colSum[i][j] = colSum[i - 1][j] + grid[i - 1][j - 1];
            }
        }
        
        for (int k = Math.min(m, n); k > 1; --k) {
            for (int i = 0; i <= m - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (isMagic(grid, i, j, i + k - 1, j + k - 1)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
    
    private boolean isMagic(int[][] grid, int r1, int c1, int r2, int c2) {
        long target = rowSum[r1 + 1][c2 + 1] - rowSum[r1 + 1][c1];
        
        for (int r = r1 + 1; r <= r2; ++r) {
            if (rowSum[r + 1][c2 + 1] - rowSum[r + 1][c1] != target) {
                return false;
            }
        }
        
        for (int c = c1; c <= c2; ++c) {
            if (colSum[r2 + 1][c + 1] - colSum[r1][c + 1] != target) {
                return false;
            }
        }
        
        long diag1 = 0;
        for (int i = 0; i <= r2 - r1; ++i) {
            diag1 += grid[r1 + i][c1 + i];
        }
        if (diag1 != target) return false;
        
        long diag2 = 0;
        for (int i = 0; i <= r2 - r1; ++i) {
            diag2 += grid[r1 + i][c2 - i];
        }
        return diag2 == target;
    }
}
