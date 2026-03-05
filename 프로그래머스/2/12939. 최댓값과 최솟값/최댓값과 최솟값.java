import java.util.*;
class Solution {
    public String solution(String s) {
        
        return findSmallAndMax(s);
    }
    
    public String findSmallAndMax(String s){
        
        List<Integer> list = new ArrayList<>();
        
        int idx = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){
                list.add(Integer.valueOf(s.substring(idx,i)));
                idx = i+1;
                continue;
            }
            if(i==s.length()-1){
                list.add(Integer.valueOf(s.substring(idx, i+1)));
            }
        }
        
        Collections.sort(list);
        
        return String.valueOf(list.get(0)) + " " + String.valueOf(list.get(list.size()-1)); 
        
    }
}