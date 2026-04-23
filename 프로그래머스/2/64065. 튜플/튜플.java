import java.util.*;
class Solution {
    
    List<int[]> list = new ArrayList<>();
    
    public int[] solution(String s) {
        
        makeListByString(s);
        
        Collections.sort(list, (o1,o2)->o1.length-o2.length);
        List<Integer> answerList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0;i<list.size();i++){
            int []tmp = list.get(i);
            
            for(int j=0;j<tmp.length;j++){
                if(!set.contains(tmp[j])){
                    set.add(tmp[j]);
                    answerList.add(tmp[j]);
                    break;
                }
            }
            
            
        }
        return answerList.stream().mapToInt(i->i).toArray();
    }
    
    public void makeListByString(String s){
        
        
        int first = s.indexOf("{",1);
        int second = s.indexOf("}");
        
        while(true){
            String tmp = s.substring(first+1,second);
            String []tmpNumber = tmp.split(",");
            int []tmpINumber = new int[tmpNumber.length];
            
            for(int i=0;i<tmpNumber.length;i++){
                tmpINumber[i] = Integer.valueOf(tmpNumber[i]);
            }
            
            list.add(tmpINumber);
            
            first = s.indexOf("{",first+1);
            second = s.indexOf("}",second+1);
            
            if(first == -1){
                break;
            }
        }
    }
}