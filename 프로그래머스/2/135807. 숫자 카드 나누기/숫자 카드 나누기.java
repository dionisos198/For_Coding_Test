import java.util.*;
class Solution {
    
    public int gcd(int a, int b){
        
        if(a%b==0){
            return b;
        }
        
        return gcd(b,a%b);
    }
    
    public int getMaxGCD(int []arr){
        int value = arr[0];
        for(int i=1;i<arr.length;i++){
            value = gcd(value, arr[i]);
        }
        
        return value;
    }
    
    public int getMaxValue(int []arr, int brr[]){
        
        int maxGCDA = getMaxGCD(arr);
        
        for(int i=0;i<brr.length;i++){
            
            if(brr[i]%maxGCDA==0){
                maxGCDA = 0;
                break;
            }
        }
        
        int maxGCDB = getMaxGCD(brr);
        
        for(int i=0;i<arr.length;i++){
            
            if(arr[i]%maxGCDB==0){
                maxGCDB = 0;
                break;
            }
        }
        
        return Math.max(maxGCDA, maxGCDB);
        
    }
    public int solution(int[] arrayA, int[] arrayB) {
    
        return getMaxValue(arrayA,arrayB);
    }
}