import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        
        List<Long> answerList = new ArrayList<>();
        
        for(long number: numbers){
            
            String bLong = Long.toBinaryString(number);
            
            answerList.add(Long.parseLong(greaterThan(bLong),2));
            
        }
        
        return answerList.stream().mapToLong(i->i).toArray();
    }
    
    public String greaterThan(String bLong){
        
     //   System.out.println(bLong);
        
        StringBuilder sb = new StringBuilder(bLong);
        
        if(sb.charAt(bLong.length()-1)=='0'){
            sb.setCharAt(bLong.length()-1,'1');
            return sb.toString();
        }
        
        for(int i=bLong.length()-1;i>=0;i--){
            if(sb.charAt(i)=='0'){
                sb.setCharAt(i,'1');
                sb.setCharAt(i+1,'0');
                break;
            }
            
            if(i==0){
                sb.setCharAt(i,'0');
                sb.insert(0,"1");
            }
        }
        
   //     System.out.println(sb.toString());
        
        return sb.toString();
    }
    
    
}