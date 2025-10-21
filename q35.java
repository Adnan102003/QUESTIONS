PROBLEM:::
You are given an integer array nums and two integers k and numOperations.

You must perform an operation numOperations times on nums, where in each operation you:

Select an index i that was not selected in any previous operations.
Add an integer in the range [-k, k] to nums[i].
Return the maximum possible frequency of any element in nums after performing the operations.

 

Example 1:

Input: nums = [1,4,5], k = 1, numOperations = 2

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1]. nums becomes [1, 4, 5].
Adding -1 to nums[2]. nums becomes [1, 4, 4].
Example 2:

Input: nums = [5,11,20,20], k = 5, numOperations = 1

Output: 2

Explanation:

We can achieve a maximum frequency of two by:

Adding 0 to nums[1].
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
0 <= k <= 105
0 <= numOperations <= nums.length


SOLUTION:::
import java.util.*;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxFreq = 0;
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        for (int i = 0; i < n; i++) {
            int target = nums[i];
            
            int left = lowerBound(nums, target - k);
            int right = upperBound(nums, target + k);
            
            int inRange = right - left;
            int alreadyEqual = freq.get(target);
            int canChange = inRange - alreadyEqual;
            
            int frequency = alreadyEqual + Math.min(canChange, numOperations);
            maxFreq = Math.max(maxFreq, frequency);
        }
        
        for (int i = 0; i < n; i++) {
            int target = nums[i] + k;
            
            int left = lowerBound(nums, target - k);
            int right = upperBound(nums, target + k);
            
            int inRange = right - left;
            int alreadyEqual = freq.getOrDefault(target, 0);
            int canChange = inRange - alreadyEqual;
            
            int frequency = alreadyEqual + Math.min(canChange, numOperations);
            maxFreq = Math.max(maxFreq, frequency);
        }
        
        for (int i = 0; i < n; i++) {
            int target = nums[i] - k;
            
            int left = lowerBound(nums, target - k);
            int right = upperBound(nums, target + k);
            
            int inRange = right - left;
            int alreadyEqual = freq.getOrDefault(target, 0);
            int canChange = inRange - alreadyEqual;
            
            int frequency = alreadyEqual + Math.min(canChange, numOperations);
            maxFreq = Math.max(maxFreq, frequency);
        }
        
        return maxFreq;
    }
    
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    private int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}