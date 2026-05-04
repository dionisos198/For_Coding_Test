import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        for(int i=1;i<=elements.length;i++){
            List<Integer> tmp = new ArrayList<>();
            int sum = 0;
            for(int j=0;j<i;j++){
                sum += elements[j];
            }
            tmp.add(sum);
            
            for(int j=0;j<elements.length;j++){
                sum += elements[(j+i)%(elements.length)];
                sum -= elements[j];
                set.add(sum);
                tmp.add(sum);
            }
            
           // System.out.println(tmp);
        }
        
        return set.size();
    }
}