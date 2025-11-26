PROBLEM:::
You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.

Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:


Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
Output: 2
Explanation: There are two paths where the sum of the elements on the path is divisible by k.
The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
Example 2:


Input: grid = [[0,0]], k = 5
Output: 1
Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
Example 3:


Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
Output: 10
Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 5 * 104
1 <= m * n <= 5 * 104
0 <= grid[i][j] <= 100
1 <= k <= 50


SOLUTION:::
class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;
        int[][] prev = new int[n][k];
        int[][] curr = new int[n][k];
        
        prev[0][grid[0][0] % k] = 1;
        for (int j = 1; j < n; j++) {
            for (int rem = 0; rem < k; rem++) {
                int newRem = (rem + grid[0][j]) % k;
                prev[j][newRem] = (prev[j][newRem] + prev[j-1][rem]) % MOD;
            }
        }
        
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(curr[j], 0);
            }
            
            for (int rem = 0; rem < k; rem++) {
                int newRem = (rem + grid[i][0]) % k;
                curr[0][newRem] = (curr[0][newRem] + prev[0][rem]) % MOD;
            }
            
            for (int j = 1; j < n; j++) {
                for (int rem = 0; rem < k; rem++) {
                    int newRem = (rem + grid[i][j]) % k;
                    curr[j][newRem] = (curr[j][newRem] + prev[j][rem]) % MOD;
                    curr[j][newRem] = (curr[j][newRem] + curr[j-1][rem]) % MOD;
                }
            }
            
            // Swap arrays
            int[][] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n-1][0];
    }
}