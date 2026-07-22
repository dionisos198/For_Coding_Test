import java.util.*;
class Solution {
    
    Map<String,MusicBasicInfo> map = new HashMap<>();
    
    class MusicBasicInfo{
        int index;
        String melody;
        
        public MusicBasicInfo(int index, String melody){
            this.index = index;
            this.melody = melody;
        }
        
    }
    
    public int getMinute(String startTime, String endTime){
        
        String []startTimeInfo = startTime.split(":");
        String []endTimeInfo = endTime.split(":");
        
        return (Integer.valueOf(endTimeInfo[0]) * 60 + Integer.valueOf(endTimeInfo[1])) -
                (Integer.valueOf(startTimeInfo[0]) * 60 + Integer.valueOf(startTimeInfo[1]));
    }
    
    int order = 1;
    
    public void makeMelody(String []musicinfo){
        
        int diff = getMinute(musicinfo[0], musicinfo[1]);
        
        StringBuilder sb = new StringBuilder("");
        int index = 0;
        
        for(int i=0;i<diff;i++){
            if(index == musicinfo[3].length()){
                index = 0;
            }
            sb.append(musicinfo[3].charAt(index));
            if(index+1!=musicinfo[3].length() && musicinfo[3].charAt(index+1)=='#'){
                sb.append("#");
                index++;
            }
            index++;
        }
        
        map.put(musicinfo[2], new MusicBasicInfo(order++,sb.toString()));
                
    }
    public String solution(String m, String[] musicinfos) {
        
        for(String musicinfo: musicinfos){
            
            makeMelody(musicinfo.split(","));
        }
        
        List<String> answerList = new ArrayList<>();
        
        for(String title: map.keySet()){
            String melody = map.get(title).melody;
            int idx = melody.indexOf(m);
            while(idx != -1){
               int finalIndex = idx + m.length();
               if(finalIndex == melody.length()){
                   answerList.add(title);
                   break;
               }
               else if(melody.charAt(finalIndex)!='#'){
                   answerList.add(title);
                   break;
               }
                
                idx = melody.indexOf(m,idx+1);                
            }
        }
        
        if(answerList.isEmpty()){
            return "(None)";
        }
        
        Collections.sort(answerList, (o1,o2)-> {
            if(map.get(o1).melody.length()==map.get(o2).melody.length()){
                return map.get(o1).index - map.get(o2).index;
            }
            
            return map.get(o2).melody.length()-map.get(o1).melody.length();
        });
        
        return answerList.get(0);
        
        
    }
}