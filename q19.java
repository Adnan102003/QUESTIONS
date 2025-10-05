PROBLEM::
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.


SOLUTION::
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;
        
        int m = heights.length;
        int n = heights[0].length;
        
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            pacificQueue.offer(new int[]{i, 0});
            pacific[i][0] = true;
        }
        for (int j = 1; j < n; j++) {
            pacificQueue.offer(new int[]{0, j});
            pacific[0][j] = true;
        }
        
        for (int i = 0; i < m; i++) {
            atlanticQueue.offer(new int[]{i, n - 1});
            atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n - 1; j++) {
            atlanticQueue.offer(new int[]{m - 1, j});
            atlantic[m - 1][j] = true;
        }
        
        bfs(heights, pacificQueue, pacific, m, n);
        bfs(heights, atlanticQueue, atlantic, m, n);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited, int m, int n) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }
                
                if (visited[newRow][newCol]) {
                    continue;
                }
                
                if (heights[newRow][newCol] >= heights[row][col]) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}