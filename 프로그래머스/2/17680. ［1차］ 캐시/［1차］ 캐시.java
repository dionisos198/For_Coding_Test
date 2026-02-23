import java.util.*;
class Solution {
    
    int time = 0;
    class LRUCache{
        
        int size;
        Set<String> cities = new LinkedHashSet<>();
        
        public LRUCache(int size){
            this.size = size;
        }
        
        public void addCache(String city){
            
            if(cities.contains(city)){
                time+=1;
                cities.remove(city);
                cities.add(city);
                return;
            }
            
            time += 5;
            cities.add(city);
        //    System.out.println(cities.size()+" "+this.size);
            if(cities.size()>this.size){
                String first = cities.iterator().next();
                cities.remove(first);
            }
            
        }
        
        
    }
    public int solution(int cacheSize, String[] cities) {
        LRUCache lc = new LRUCache(cacheSize);
        
        for(String city: cities){
            lc.addCache(city.toLowerCase());
        }
        
        return this.time;
    }
}