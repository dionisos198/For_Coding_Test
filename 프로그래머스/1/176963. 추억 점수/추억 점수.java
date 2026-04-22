import java.util.*;
class Solution {
    Map<String,Integer> score = new HashMap<>();
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        
        initMap(name, yearning);
        
        List<Integer> answer = new ArrayList<>();
        
        for(String []p: photo){
            int sum = 0;
            for(int i=0;i<p.length;i++){
                if(!score.containsKey(p[i])){
                    continue;
                }
                sum += score.get(p[i]);
            }
            
            answer.add(sum);
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
    
    public void initMap(String []name, int []yearning){
        for(int i=0;i<name.length;i++){
            score.put(name[i], yearning[i]);
        }
    }
}