import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        
        Set<String> set = new HashSet<>();
        String beforeWord = "";
        
        int number = 0;
        for(int i=0;i<words.length;i++){
            number++;
            if(set.contains(words[i]) || 
               (i!=0 &&beforeWord.charAt(beforeWord.length()-1)!= words[i].charAt(0))){
                break;
            }
            set.add(words[i]);
            beforeWord = words[i];
        }
        
        if(set.size()==words.length){
            return new int[]{0,0};
        }
        
        int first = number % n==0?n:number%n;
        int second = number % n ==0? number/n: (number/n) + 1;
        
   //     System.out.println(first+" "+second);
        
        return new int[]{first,second};
        
        
        
        
    }
}