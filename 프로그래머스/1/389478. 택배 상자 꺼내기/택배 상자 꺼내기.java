class Solution {
    
    public void fillArr(int arr[][], int w, int n){
        int i =1;
        int j = 1;
        int k = 1;
        boolean right = true;
        while(n>0){
            arr[i][j] = k;
            
            if(k%w==0){
                i = i+1;
                right = !right;
            }
            
            else if(right){
                j++;
            }
            else if(!right){
                j--;
            }
            
            k++;
            n--;
            
        }
    }
    
    public void print(int arr[][]){
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    
 
    public int solution(int n, int w, int num) {
        
        int arr[][] = new int[101][w+1];
        fillArr(arr,w,n);
        
        print(arr);
        
        int thatI = 0;
        int thatJ = 0;
        for(int i=1;i<101;i++){
            for(int j=1;j<=w;j++){
                if(arr[i][j]==num){
                    thatI = i;
                    thatJ = j;
                    break;
                }
            }
        }
        
        int ans = 0;
        
        for(int i = thatI;i<101;i++){
            if(arr[i][thatJ]!=0){
                ans++;
                continue;
            }
            break;
        }
        
        
        return ans;
        
 
        
        
    }
}