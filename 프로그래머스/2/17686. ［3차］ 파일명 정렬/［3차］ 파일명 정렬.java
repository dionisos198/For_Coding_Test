import java.util.*;
class Solution {
    
    class KakaoString implements Comparable<KakaoString> {
        
        private String head;
        private String number;
        private String tail;
        private int sequence;
        
        public KakaoString(String head, String number, String tail,int sequence){
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.sequence = sequence;
        }
        
        @Override
        public int compareTo(KakaoString ks){
            
            if(ks.head.toLowerCase().equals(this.head.toLowerCase())){
                if(Integer.valueOf(this.number)==Integer.valueOf(ks.number)){
                    return this.sequence - ks.sequence;
                }
                
                return Integer.valueOf(this.number) - Integer.valueOf(ks.number);
            }
            
            return this.head.toLowerCase().compareTo(ks.head.toLowerCase());
        }
        
        public String getEntireName(){
            return head + number + tail;
        }
    }
    
    public String[] divider3(String fileName){
        
        String head="";
        String number="";
        String tail="";
        boolean numberAppear = false;
        
        for(int i=0;i<fileName.length();i++){
            if(fileName.charAt(i)>='0' && fileName.charAt(i)<='9'){
                numberAppear = true;
                number+=String.valueOf(fileName.charAt(i));
                continue;
            }
            if(!numberAppear){
                head += String.valueOf(fileName.charAt(i));
                continue;
            }
            
            tail += fileName.substring(i, fileName.length());
            break;
        }
        
        return new String[]{head,number,tail};
    }
    
    public String[] solution(String[] files) {
        List<KakaoString> kakaoStringList = new ArrayList<>();
        
        for(int i=0;i<files.length;i++){
            String[] info = divider3(files[i]);
     //       System.out.println(info[0]+" "+info[1]+" "+info[2]);
            
            kakaoStringList.add(new KakaoString(info[0],info[1],info[2],i));
        }
        
        Collections.sort(kakaoStringList);
        
        String []answer = new String[kakaoStringList.size()];
        
        for(int i=0;i<answer.length;i++){
            answer[i] = kakaoStringList.get(i).getEntireName();
        }
        
        return answer;
        
        
        
    }
}