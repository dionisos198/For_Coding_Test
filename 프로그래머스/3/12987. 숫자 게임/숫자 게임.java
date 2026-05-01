import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<B.length;i++){
            pq.add(B[i]);
        }
        
        int score = 0;
        for(int i=0;i<A.length;i++){
            
            int target = A[i];
            
            while(!pq.isEmpty() && pq.peek()<=target){
                pq.poll();
            }
            
            if(!pq.isEmpty()){
                pq.poll();
                score++;
            }
            else{
                break;
            }
        }
        
        return score;
    }
    
    // target보다 큰 바로 다음 거 
//     public int binarySearch(int []B, int target){
        
//         int left = 0;
//         int right = B.length-1;
        
//         while(left <= right){
//             int mid = (left + right) / 2 ;
            
//             if(B[mid]>target){
//                 right = mid-1;
//             }
//             else if(B[mid]<=target){
//                 left = mid+1;
//             }
//         }
        
//         return B[left-1];
//     }
}