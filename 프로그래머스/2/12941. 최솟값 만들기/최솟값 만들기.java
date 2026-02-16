import java.util.*;
class Solution
{
    
    int result = Integer.MAX_VALUE;
    public int solution(int []A, int []B)
    {
       // BT(A,B,0, new boolean[A.length], new boolean[B.length], 0);
        Arrays.sort(A);
        Arrays.sort(B);
        
        int sum = 0;
        for(int i=0;i<A.length;i++){
            sum += A[i] * B[A.length-1-i];
        }
        

        return sum;
    }
    
    public void BT(int []A, int []B,int count, boolean []aUsed, boolean []bUsed,int sum){
        
        if(A.length== count){
            result = Math.min(sum, result);
            return;
        }
        
        for(int i=0;i<A.length;i++){
            if(!aUsed[i]){
                for(int j=0;j<B.length;j++){
                    if(!bUsed[j]){
                        aUsed[i]=true;
                        bUsed[j]=true;
                        BT(A,B,count+1,aUsed,bUsed,sum+A[i]*B[j]);
                        aUsed[i] = false;
                        bUsed[j] = false;
                    }
                }
            }
        }
    }
}