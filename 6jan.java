Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

 

Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105

SOLUTION:::
import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        int level = 0;
        int bestLevel = 1;
        long bestSum = Long.MIN_VALUE;
        
        while (!q.isEmpty()) {
            int size = q.size();
            long currSum = 0;
            level++;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                currSum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            
            if (currSum > bestSum) {
                bestSum = currSum;
                bestLevel = level;
            }
        }
        
        return bestLevel;
    }
}
