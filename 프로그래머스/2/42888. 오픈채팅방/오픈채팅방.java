import java.util.*;
class Solution {
    
    Map<String,String> nameMap = new HashMap<>();
    public String[] solution(String[] record) {
        
        List<String> answerList = new ArrayList<>();
        
        for(int i=0;i<record.length;i++){
            String[] splited = record[i].split(" ");
            
            String order = splited[0];
            String id = splited[1];
 
            if(order.equals("Enter")){
               answerList.add(id+"님이 들어왔습니다.");  
               String nickName = splited[2];
               nameMap.put(id, nickName);
            }
            else if(order.equals("Leave")){
                answerList.add(id+"님이 나갔습니다.");
            }
            else{
                String nickName = splited[2];
                nameMap.put(id, nickName);
            }
            
        }
        
        for(int i = 0;i<answerList.size();i++){
            String fullText = answerList.get(i);
            
            int index = fullText.indexOf("님");
            fullText = fullText.replace(fullText.substring(0,index), nameMap.get(fullText.substring(0,index)));
            answerList.set(i, fullText);
        }
        
        return answerList.toArray(new String[0]);
    }
}