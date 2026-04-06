import java.util.*;
class Solution {
    public int solution(int[][] signals) {
        int time = 1;
        int max = getMax(signals);
        while(time<=max){

            boolean allYellow = true;
            for(int i=0;i< signals.length;i++){
                String currentColor = getColor(time, signals[i]);
            //    System.out.println(time+" "+currentColor);
                if(!currentColor.equals("YELLOW")){
                    allYellow = false;
                    break;
                }
            }
            
            if(allYellow){
                return time;
            }
            time++;
        }
        
        return -1;
        
        
    }
    
    public int getMax(int [][]signals){
        int max = 1;
        for(int i=0;i<signals.length;i++){
            max *= (signals[i][0] + signals[i][1] + signals[i][2]);
        }
        return max;
    }
    
    public String getColor(int time, int []colors){
        int tmp = time % (colors[0] + colors[1] + colors[2]);
     //   System.out.println(tmp);
        
        if(1<=tmp && tmp<=colors[0]){
            return "GREEN";
        }
        else if(tmp>colors[0] && tmp<= colors[0] + colors[1]){
            return "YELLOW";
        }
        
        return "RED";
        
    }
}