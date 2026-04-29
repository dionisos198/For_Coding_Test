class Solution {
    public int solution(int[] arr) {
        if(arr.length==1){
            return arr[0];
        }
        
        // 최소 공배수를 구하는 방법: 곱하기 후 최대 공약수로 나누기 
        int answer = (arr[0] * arr[1]) / gcd(arr[0],arr[1]);
        
        // 그거를 반복하는 식으로 가능하다. 
        for(int i=2;i<arr.length;i++){
            answer = (answer * arr[i]) / gcd(answer,arr[i]);
        }
        
        
        
        return answer;
    }
    
    
    // 최대 공약수 
    int gcd(int a, int b){
        int r = a% b;
        if(r==0) return b;
        return gcd(b,r);
    }
}