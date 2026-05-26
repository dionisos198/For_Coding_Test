import java.util.*;
class Solution {
    Map<String,Integer> map = new HashMap<>();
    int index = 27;
    
    public int[] solution(String msg) {
        List<Integer> answerList = new ArrayList<>();
        init();
        
        for(int i=0;i<msg.length();i++){

          int nextIndex = i + 1;

          for(int j=i+1;j<=msg.length();j++){

            if(map.containsKey(msg.substring(i,j))){
               nextIndex = j;
             } else {
              break;
             }
            }

    answerList.add(map.get(msg.substring(i,nextIndex)));

    if(nextIndex < msg.length()){
        map.put(msg.substring(i,nextIndex+1), index++);
    }

    i = nextIndex - 1;
}
        
        return answerList.stream().mapToInt(i->i).toArray();
    }
    
    public void init(){
        
        for(int i=0;i<26;i++){
            map.put(String.valueOf((char)('A' + i)), i + 1);
        }
    }
}