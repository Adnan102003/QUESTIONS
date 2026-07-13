import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        String digits = "123456789";
        
        for (int len = 2; len <= 9; len++) {
            for (int i = 0; i <= 9 - len; i++) {
                int num = Integer.parseInt(digits.substring(i, i + len));
                
                if (num >= low && num <= high) {
                    res.add(num);
                }
            }
        }
        
        return res;
    }
}