Given an unsorted array arr[] of size n, containing elements from the range 1 to n, it is known that one number in this range is missing, and another number occurs twice in the array, find both the duplicate number and the missing number.

Examples:

Input: arr[] = [2, 2]
Output: [2, 1]
Explanation: Repeating number is 2 and the missing number is 1.
Input: arr[] = [1, 3, 3] 
Output: [3, 2]
Explanation: Repeating number is 3 and the missing number is 2.
Input: arr[] = [4, 3, 6, 2, 1, 1]
Output: [1, 5]
Explanation: Repeating number is 1 and the missing number is 5.
Constraints:
2 ≤ n ≤ 106
1 ≤ arr[i] ≤ n


SOLUTION:::
class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        int n = arr.length;
        long sumN = (long) n * (n + 1) / 2;
        long sumSqN = (long) n * (n + 1) * (2 * n + 1) / 6;
        
        long sumArr = 0;
        long sumSqArr = 0;
        
        for (int num : arr) {
            sumArr += num;
            sumSqArr += (long) num * num;
        }
        
        long diff = sumArr - sumN;
        long sumDiff = sumSqArr - sumSqN;
        
        long sum = sumDiff / diff;
        
        int repeating = (int) ((diff + sum) / 2);
        int missing = (int) (sum - repeating);
        
        ArrayList<Integer> result = new ArrayList<>();
        result.add(repeating);
        result.add(missing);
        
        return result;
    }
}
