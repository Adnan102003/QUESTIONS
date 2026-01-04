Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.

 

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation: 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
Example 2:

Input: nums = [21,21]
Output: 64
Example 3:

Input: nums = [1,2,3,4,5]
Output: 0
 

Constraints:

1 <= nums.length <= 104
1 <= nums[i] <= 105


SOLUTION::::
class Solution {
    public int sumFourDivisors(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += getSumIfFourDivisors(num);
        }
        return total;
    }

    private int getSumIfFourDivisors(int num) {
        int count = 0;
        int sum = 0;

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                int j = num / i;

                count++;
                sum += i;

                if (i != j) {
                    count++;
                    sum += j;
                }

                if (count > 4) {
                    return 0;
                }
            }
        }

        return count == 4 ? sum : 0;
    }
}
