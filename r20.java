You are given 3 positive integers zero, one, and limit.

A binary array arr is called stable if:

The number of occurrences of 0 in arr is exactly zero.
The number of occurrences of 1 in arr is exactly one.
Each subarray of arr with a size greater than limit must contain both 0 and 1.
Return the total number of stable binary arrays.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: zero = 1, one = 1, limit = 2

Output: 2

Explanation:

The two possible stable binary arrays are [1,0] and [0,1].

Example 2:

Input: zero = 1, one = 2, limit = 1

Output: 1

Explanation:

The only possible stable binary array is [1,0,1].

Example 3:

Input: zero = 3, one = 3, limit = 2

Output: 14

Explanation:

All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0], [1,1,0,0,1,0], and [1,1,0,1,0,0].

 

Constraints:

1 <= zero, one, limit <= 1000


SOLUTION:::
class Solution {
    public int numberOfStableArrays(int zeroCount, int oneCount, int limit) {
        int MODULO = 1000000007;
        long[][][] storage = new long[zeroCount + 1][oneCount + 1][2];
        for (int i = 1; i <= Math.min(zeroCount, limit); i++) storage[i][0][0] = 1;
        for (int j = 1; j <= Math.min(oneCount, limit); j++) storage[0][j][1] = 1;
        for (int i = 1; i <= zeroCount; i++) {
            for (int j = 1; j <= oneCount; j++) {
                storage[i][j][0] = (storage[i - 1][j][0] + storage[i - 1][j][1]) % MODULO;
                if (i > limit) {
                    storage[i][j][0] = (storage[i][j][0] - storage[i - limit - 1][j][1] + MODULO) % MODULO;
                }
                storage[i][j][1] = (storage[i][j - 1][0] + storage[i][j - 1][1]) % MODULO;
                if (j > limit) {
                    storage[i][j][1] = (storage[i][j][1] - storage[i][j - limit - 1][0] + MODULO) % MODULO;
                }
            }
        }
        return (int) ((storage[zeroCount][oneCount][0] + storage[zeroCount][oneCount][1]) % MODULO);
    }
}
