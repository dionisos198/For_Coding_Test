import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String tt: timetable){
            pq.add(makeToNum(tt));
        }
        
        int time = getAnswer(n,t,m,pq);
     //   System.out.println("time: "+time);
        
        
        return makeToString(time);
    }
    
    public String makeToString(int time){
        int hour = time / 60;
        int minute = time % 60;
        
        StringBuilder sb = new StringBuilder();
        if(hour<10){
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");
        if(minute<10){
            sb.append("0");
        }
        sb.append(minute);
        
        return sb.toString();
    }
    
    public int makeToNum(String time){
        
        String t[] = time.split(":");
        return Integer.valueOf(t[0]) * 60 + Integer.valueOf(t[1]);
    }
    
    public int getAnswer(int n, int t, int m, PriorityQueue<Integer> pq){
        
        int startTime = 60 * 9;
        int tmpAnswer = 0;
        
        for(int i = 0; i < n; i++){
            
            List<Integer> tmpGuest = new ArrayList<>();
            while(!pq.isEmpty() && tmpGuest.size() < m && pq.peek()<=startTime){
                tmpGuest.add(pq.poll());
            }
            
          //  System.out.println(tmpGuest);
            
            if(tmpGuest.size() < m){
                tmpAnswer = startTime;
            }
            else{
                tmpAnswer = tmpGuest.get(m-1) -1;
            }
            
            startTime += t;
            
        }
        
        return tmpAnswer;
    }
}