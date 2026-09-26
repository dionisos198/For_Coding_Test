import java.util.*;
class Solution {
    
    int [][]points;
    
    public class Robot{
        
        int []currentPosition;
        int []routes;
        int currentIndex;
        
        public Robot(int []currentPosition, int []routes){
            this.currentPosition = currentPosition;
            this.routes = routes;
            currentIndex = 0;
        }
        
        @Override
        public boolean equals(Object o){
            Robot r = (Robot) o;
            return currentPosition[0]== r.currentPosition[0] && 
                currentPosition[1] == r.currentPosition[1];
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(currentPosition[0], currentPosition[1]);
        }
        
        private boolean isEnd(){
            return this.currentIndex == routes.length-1;
        }
        
        public void move(){
            
            if(isEnd()){
                return;
            }
            
            int destY = points[routes[currentIndex+1]-1][0];
            int destX = points[routes[currentIndex+1]-1][1];
            int currentY = currentPosition[0];
            int currentX = currentPosition[1];
            
            if(currentY<destY){
                currentY++;
            }
            else if(currentY>destY){
                currentY--;
            }
            else if(currentY == destY){
                if(currentX<destX){
                    currentX++;
                }
                else if(currentX>destX){
                    currentX--;
                }
            }
            
            currentPosition[0] = currentY;
            currentPosition[1] = currentX;
            
            if(currentX == destX && currentY == destY){
                currentIndex++;
            }
        }
        
    }
    
    List<Robot> robots = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        init(routes);
        
        int ans = 0;
        Map<Robot, Integer> first = new HashMap<>();
        for (Robot robot : robots) {
          first.put(robot, first.getOrDefault(robot, 0) + 1);
        }
        for (int cnt : first.values()) {
          if (cnt >= 2) ans++;
        }
        
        while(true){
            
            boolean allEnd = true;
            for(Robot robot: robots){
                if(!robot.isEnd()){
                    allEnd = false;
                    break;
                }
            }
            
            if(allEnd){
                break;
            }
            
            // 2개 이상 중복된 좌표가 있으면 ans++;
            Map<Robot,Integer> robotMap = new HashMap();
            for(Robot robot: robots){
                if(!robot.isEnd()){
                    robot.move();
                    robotMap.put(robot, robotMap.getOrDefault(robot,0)+1);
                }
            }
            
            for(Robot robot: robotMap.keySet()){
                if(robotMap.get(robot)>=2){
                    ans++;
                }
            }
            
        }
        return ans;
    }
    
    public void init(int [][]routes){
        
        for(int i=0;i<routes.length;i++){
            
            int currentY = points[routes[i][0]-1][0];
            int currentX = points[routes[i][0]-1][1];
            robots.add(new Robot(new int[]{currentY, currentX}, routes[i]));
        }
    }
}