import java.util.*;
class Solution {
    Set<String> spoilerWord = new HashSet<>();
    Set<String> nonSpoilerWord = new HashSet<>();
    public int solution(String message, int[][] spoiler_ranges) {
        
        int startIndex = 0;
        int endIndex = 0;
        
       for(int i=0;i<message.length();i++){
           if(message.charAt(i)==' ' || i == message.length()-1){
               if(i == message.length()-1){
                   endIndex = i;
               }
               else{
                endIndex = i-1;   
               }
               if(!isDuplicate(startIndex, endIndex, spoiler_ranges)){
                   nonSpoilerWord.add(message.substring(startIndex,endIndex+1));
               }
               else{
                   spoilerWord.add(message.substring(startIndex,endIndex+1));
               }
               startIndex = i+1;
               continue;
           }
                      
       }
       
        List<String> deleteWord = new ArrayList<>();
       for(String s : spoilerWord){
           if(nonSpoilerWord.contains(s)){
               deleteWord.add(s);
           }
       }
        
        for(String d: deleteWord){
            spoilerWord.remove(d);
        }
   //     System.out.println(spoilerWord);
    //    System.out.println(nonSpoilerWord);
    //    System.out.println(deleteWord);
        
        
        return spoilerWord.size();
    }
    
    public boolean isDuplicate(int startIndex, int endIndex, int [][]spoiler_ranges){
        for(int i=0;i<spoiler_ranges.length;i++){
            int s = spoiler_ranges[i][0];
            int e = spoiler_ranges[i][1];
            
            if(e<startIndex || s>endIndex){
                continue;
            }
            return true;
        }
        
        return false;
    }
}