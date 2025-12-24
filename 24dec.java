Given a sorted array arr[] containing distinct positive integers that has been rotated at some unknown pivot, and a value x. Your task is to count the number of elements in the array that are less than or equal to x.

Examples:

Input: arr[] = [4, 5, 8, 1, 3], x = 6
Output: 4
Explanation: 1, 3, 4 and 5 are less than 6, so the count of all elements less than 6 is 4.
Input: arr[] = [6, 10, 12, 15, 2, 4, 5], x = 14
Output: 6
Explanation: All elements except 15 are less than 14, so the count of all elements less than 14 is 6.
Constraints:
1 ≤ arr.size() ≤ 105
0 ≤ arr[i], x ≤ 109



SOLUTION:::
class Solution {
    public int countLessEqual(int[] arr, int x) {
        int n = arr.length;
        int pivot = findPivot(arr);
        
        if (pivot == -1) {
            return upperBound(arr, 0, n - 1, x);
        }
        
        int countLeft = upperBound(arr, 0, pivot, x);
        int countRight = upperBound(arr, pivot + 1, n - 1, x);
        
        return countLeft + countRight;
    }
    
    private int findPivot(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return (left == 0 && arr[left] <= arr[arr.length - 1]) ? -1 : left - 1;
    }
    
    private int upperBound(int[] arr, int left, int right, int x) {
        if (left > right) return 0;
        
        int start = left;
        int end = right + 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            if (arr[mid] <= x) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start - left;
    }
}
