import java.util.*;
class Solution {
    Map<String,Integer> map1 = new HashMap<>();
    Map<String,Integer> map2 = new HashMap<>();
    public int solution(String str1, String str2) {
        
        initMap(str1,str2);
        // for(String key: map1.keySet()){
        //     System.out.println("key: "+key+" "+map1.get(key));
        // }
        // System.out.println("=============");
        // for(String key: map2.keySet()){
        //     System.out.println("key: "+key+" "+map2.get(key));
        // }
        int min = getMin();
        int max = getMax();
        
//        System.out.println(min+" "+max);
        
        if(min==0 && max ==0) return 65536;
    
        return min * 65536 / max;
    }
    
    public int getMin(){
        int count = 0;
        for(String key: map1.keySet()){
            if(map2.containsKey(key)){
                count += Math.min(map1.get(key),map2.get(key));
            }
        }
        
        return count;
    }
    
    public int getMax(){
        int count = 0;
        for(String key: map1.keySet()){
            if(map2.containsKey(key)){
                count += Math.max(map1.get(key),map2.get(key));
            }
            else{
                count += map1.get(key);
            }
        }
        
        for(String key: map2.keySet()){
            if(!map1.containsKey(key)){
                count += map2.get(key);
            }

        }
        
        return count;
    }
    
    public void initMap(String str1, String str2){
        
        for(int i=0;i<str1.length();i+=1){
            String word = str1.substring(i,Math.min(i+2,str1.length())).toLowerCase();
            
            if(word.length()==1){
                continue;
            }
            
            if(word.charAt(0)<'a' || word.charAt(0)>'z' || word.charAt(1)<'a' || 
               word.charAt(1)>'z'){
                continue;
            }
            
       
            if(!map1.containsKey(word)){
                map1.put(word,0);
            }
            
            map1.put(word,map1.get(word)+1);
        }
        
        for(int i=0;i<str2.length();i+=1){
            String word = str2.substring(i,Math.min(i+2,str2.length())).toLowerCase();
                                         
                                                     
            if(word.length()==1){
                continue;
            }
            
            if(word.charAt(0)<'a' || word.charAt(0)>'z' || word.charAt(1)<'a' || 
               word.charAt(1)>'z'){
                continue;
            }
       
            if(!map2.containsKey(word)){
                map2.put(word,0);
            }
            
            map2.put(word,map2.get(word)+1);
        }
        
    }
}