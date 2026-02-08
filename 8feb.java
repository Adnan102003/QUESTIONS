Given a binary tree, determine if it is height-balanced.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:


Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:

Input: root = []
Output: true
 

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104

SOLUTION:::
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }    
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }        
        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1;
        }        
        int rightHeight = height(node.right);
        if (rightHeight == -1) {
            return -1;
        }        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
