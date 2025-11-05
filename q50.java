PROBLEM:::
You are given an array nums of n integers and two integers k and x.

The x-sum of an array is calculated by the following procedure:

Count the occurrences of all elements in the array.
Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
Calculate the sum of the resulting array.
Note that if an array has less than x distinct elements, its x-sum is the sum of the array.

Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

 

Example 1:

Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2

Output: [6,10,12]

Explanation:

For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.
Example 2:

Input: nums = [3,8,7,8,7,5], k = 2, x = 2

Output: [11,15,15,15,12]

Explanation:

Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

 

Constraints:

nums.length == n
1 <= n <= 105
1 <= nums[i] <= 109
1 <= x <= k <= nums.length

SOLUTION:::
class Solution {
    class Element {
        int val;
        int freq;
        
        Element(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
    
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] answer = new long[n - k + 1];
        
        Comparator<Element> comp = (a, b) -> {
            if (a.freq != b.freq) return b.freq - a.freq;
            return b.val - a.val;
        };
        
        TreeSet<Element> topX = new TreeSet<>(comp);
        TreeSet<Element> rest = new TreeSet<>(comp);
        Map<Integer, Integer> freqMap = new HashMap<>();
        long topSum = 0;
        
        for (int i = 0; i < k; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            rest.add(new Element(entry.getKey(), entry.getValue()));
        }
        
        while (topX.size() < x && !rest.isEmpty()) {
            Element elem = rest.pollFirst();
            topX.add(elem);
            topSum += (long) elem.val * elem.freq;
        }
        
        answer[0] = topSum;
        
        for (int i = k; i < n; i++) {
            int removeVal = nums[i - k];
            int addVal = nums[i];
            
            int oldFreq = freqMap.get(removeVal);
            Element oldElem = new Element(removeVal, oldFreq);
            
            if (topX.contains(oldElem)) {
                topX.remove(oldElem);
                topSum -= (long) removeVal * oldFreq;
            } else {
                rest.remove(oldElem);
            }
            
            if (oldFreq == 1) {
                freqMap.remove(removeVal);
            } else {
                freqMap.put(removeVal, oldFreq - 1);
                Element newElem = new Element(removeVal, oldFreq - 1);
                rest.add(newElem);
            }
            
            int newFreq = freqMap.getOrDefault(addVal, 0);
            
            if (newFreq > 0) {
                Element existingElem = new Element(addVal, newFreq);
                if (topX.contains(existingElem)) {
                    topX.remove(existingElem);
                    topSum -= (long) addVal * newFreq;
                } else {
                    rest.remove(existingElem);
                }
            }
            
            freqMap.put(addVal, newFreq + 1);
            Element updatedElem = new Element(addVal, newFreq + 1);
            rest.add(updatedElem);
            
            while (topX.size() < x && !rest.isEmpty()) {
                Element elem = rest.pollFirst();
                topX.add(elem);
                topSum += (long) elem.val * elem.freq;
            }
            
            while (!rest.isEmpty() && !topX.isEmpty()) {
                Element topRest = rest.first();
                Element bottomTop = topX.last();
                
                if (comp.compare(topRest, bottomTop) < 0) {
                    rest.pollFirst();
                    topX.pollLast();
                    
                    topSum -= (long) bottomTop.val * bottomTop.freq;
                    topSum += (long) topRest.val * topRest.freq;
                    
                    topX.add(topRest);
                    rest.add(bottomTop);
                } else {
                    break;
                }
            }
            
            answer[i - k + 1] = topSum;
        }
        
        return answer;
    }
}