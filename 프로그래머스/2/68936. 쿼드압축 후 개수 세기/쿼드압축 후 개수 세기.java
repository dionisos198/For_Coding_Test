import java.util.*;
class Solution {
    int one = 0;
    int zero = 0;
    public int[] solution(int[][] arr) {
        
        makeSmall(arr,0,0,arr.length,arr[0].length);
        return new int[]{zero,one};
    }
    
    public void makeSmall(int [][]arr, int startY, int startX, int endY, int endX){
        
     //   System.out.println(startY+" "+startX+" "+endY+" "+endX);
        
        
        boolean isAllSame = true;
        int start = arr[startY][startX];
        
        for(int i=startY;i<endY;i++){
            for(int j=startX;j<endX;j++){
                if(start != arr[i][j]){
                    isAllSame = false;
                    break;
                }
            }
            if(!isAllSame){
                break;
            }
        }
        
        if(isAllSame){
            if(start==1){
                one++;
            }
            else{
                zero++;
            }
            return;
        }
        
        
        boolean end1 = oneQuarter(arr,startY,startX,endY,endX);
        
     //   System.out.println("1: "+zero+" "+one+" "+end1);
        if(!end1){
          makeSmall(arr, startY,startX, (startY+endY)/2, (startX+endX)/2);
        }
        
        boolean end2 = twoQuarter(arr, startY, startX,endY, endX);
  //      System.out.println("2: "+zero+" "+one+" "+end2);
        if(!end2){
            makeSmall(arr,startY,(startX+endX)/2,(startY+endY)/2,endX);
        }
        
        boolean end3 = threeQuarter(arr, startY, startX, endY, endX);
    //    System.out.println("3: "+zero+" "+one+" "+end3);
        if(!end3){
            makeSmall(arr,(startY+endY)/2,startX,endY,(startX+endX)/2);
        }
        
        boolean end4 = fourQuarter(arr,startY,startX,endY,endX);
 //       System.out.println("4: "+zero+" "+one+" "+end4);
        if(!end4){
            makeSmall(arr,(startY+endY)/2,(startX+endX)/2, endY, endX);
        }
        
    }
    
    public boolean oneQuarter(int [][]arr, int startY, int startX, int endY, int endX){
        
        boolean allSame = true;
        int num = arr[startY][startX];
        
        for(int i=startY;i<(startY+endY)/2;i++){
            for(int j=startX;j<(startX+endX)/2;j++){
                if(arr[i][j]!=num){
                    allSame = false;
                    break;
                }
            }
            if(!allSame) break;
        }
        
        if(allSame){
            if(num == 1){
                one++;
            }
            else{
                zero++;
            }
            
            return true;
        }
        
        return false;
          
    }
    
    public boolean twoQuarter(int [][]arr, int startY, int startX, int endY, int endX){
        
        boolean allSame = true;
        int num = arr[startY][(startX+endX)/2];
        
        for(int i=startY;i<(startY+endY)/2;i++){
            for(int j=(startX+endX)/2;j<endX;j++){
                if(arr[i][j]!=num){
                    allSame = false;
                    break;
                }
            }
            if(!allSame) break;
        }
        
        if(allSame){
            if(num == 1){
                one++;
            }
            else{
                zero++;
            }
            
            return true;
        }
        
        return false;
          
    }
    
    
    public boolean threeQuarter(int [][]arr, int startY, int startX, int endY, int endX){
        
        boolean allSame = true;
        int num = arr[(startY+endY)/2][startX];
        
        for(int i=(startY+endY)/2;i<endY;i++){
            for(int j=startX;j<(startX+endX)/2;j++){
                if(arr[i][j]!=num){
                    allSame = false;
                    break;
                }
            }
            if(!allSame) break;
        }
        
        if(allSame){
            if(num == 1){
                one++;
            }
            else{
                zero++;
            }
            
            return true;
        }
        
        return false;
          
    }
    
    
    public boolean fourQuarter(int [][]arr, int startY, int startX, int endY, int endX){
        
        boolean allSame = true;
        int num = arr[(startY+endY)/2][(startX+endX)/2];
        
        for(int i=(startY+endY)/2;i<endY;i++){
            for(int j=(startX+endX)/2;j<endX;j++){
                if(arr[i][j]!=num){
                    allSame = false;
                    break;
                }
            }
            if(!allSame) break;
        }
        
        if(allSame){
            if(num == 1){
                one++;
            }
            else{
                zero++;
            }
            
            return true;
        }
        
        return false;
          
    }
}