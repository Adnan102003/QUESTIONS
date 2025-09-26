Problem Statement
John was learning about arrays when his friend Ram arrived. Seeing John, Ram thought of a challenge for him. He challenged John to calculate the "measurement" of an array a of size n.
Given a 0-indexed array, Ram defined the measurement of the array as the sum of the "sanities" of all the elements in the array. To calculate the sanity of each element in the array, the following operations need to be performed:

Sort the given array.
Calculate the sanity of an element in the array, which is defined as the sum of its original index (before sorting) and the index of its last occurrence in the new array (after sorting).
Your task is to help John calculate the measurement of the array. Since the output may be too large, print it modulo 10^9+7.

Note that duplicate elements may exist in the array.

Input Format
The first line contains ‘n’ denoting the number of elements in the array a.

The next line contains n elements denoting the elements of the array a.

Output Format
Print the measurement of the given array

Constraints
1<=n<=106

1<=ai<=106

Sample Testcase 0
Testcase Input
6
2 1 3 2 3 1
Testcase Output
33
Explanation
The given array is: 2 1 3 2 3 1
After sorting array it become: 1 1 2 2 3 3.

For the first 1, the sanity is : 1 + 1 (sum of the original index of element before sorting and index of its last occurrence in new array)
Similarly for next 1, the sanity is: 1 + 5
For the next 2, the sanity is: 0 + 3
For the next 2, the sanity is : 3 + 3
For the next 3, the sanity is: 2 + 5
For the next 3, the sanity is: 4 + 5


So the measurement of the array is the sum of the sanities of all elements in the array : = 2 + 6 + 3 + 6 + 7 + 9 = 33

Sample Testcase 1
Testcase Input
4
1 1 1 1
Testcase Output
18
Explanation
The given array is 1 1 1 1. Array is already sorted therefore,


Sanity for the first 1 = 0 + 3.
Sanity for the second 1 = 1 + 3
Sanity for the third 1 = 2 + 3
Sanity for the fourth 1 = 3 + 3


Measurement of second array = 3 + 4 + 5 + 6 = 18

SOLUTION:::


MOD = 10**9 + 7

def calculate_measurement(n, a):
    indexed_array = [(value, idx) for idx, value in enumerate(a)]
    indexed_array.sort()
    measurement = 0
    last_occurrence = {}  
    for sorted_index, (value, original_index) in enumerate(indexed_array):
       last_occurrence[value] = sorted_index
        sanity = original_index + last_occurrence[value]
        measurement = (measurement + sanity) % MOD
    return measurement
n = int(input())  
a = list(map(int, input().split()))  
result = calculate_measurement(n, a)
print(result)