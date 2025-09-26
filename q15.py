PROBLEM::
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

 

Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Example 2:

Input: nums = [4,2,3,4]
Output: 4
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000


SOLUTION::
from typing import List

class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        nums.sort()
        count = 0
        n = len(nums)
        
        for c in range(2, n):
            left = 0
            right = c - 1
            
            while left < right:
                if nums[left] + nums[right] > nums[c]:
                    count += right - left
                    right -= 1
                else:
                    left += 1
        
        return count

class SolutionBruteForce:
    def triangleNumber(self, nums: List[int]) -> int:
        nums.sort()
        count = 0
        n = len(nums)
        
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                for k in range(j + 1, n):
                    if nums[i] + nums[j] > nums[k]:
                        count += 1
        
        return count

def test_solution():
    sol = Solution()
    
    nums1 = [2, 2, 3, 4]
    result1 = sol.triangleNumber(nums1)
    print(f"Input: {nums1}")
    print(f"Output: {result1}")
    print(f"Expected: 3")
    print()
    
    nums2 = [4, 2, 3, 4]
    result2 = sol.triangleNumber(nums2)
    print(f"Input: {nums2}")
    print(f"Output: {result2}")
    print(f"Expected: 4")
    print()
    
    nums3 = [1, 1, 1]
    result3 = sol.triangleNumber(nums3)
    print(f"Input: {nums3}")
    print(f"Output: {result3}")
    print(f"Expected: 1")
    print()
    
    nums4 = [1, 2, 3]
    result4 = sol.triangleNumber(nums4)
    print(f"Input: {nums4}")
    print(f"Output: {result4}")
    print(f"Expected: 0 (1+2 = 3, not > 3)")

if __name__ == "__main__":
    test_solution()

