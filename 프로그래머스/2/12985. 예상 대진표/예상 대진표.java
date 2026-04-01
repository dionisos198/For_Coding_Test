class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0; 
        while(true){
            if(Math.min(a,b)%2==1 && Math.max(a,b)%2==0 && Math.min(a,b)+1==Math.max(a,b)){
                return answer+1;
            }
            
            if(a%2==1){
              a = (a+1)/2;
            }
            else{
                a /=2;
            }
            
            if(b%2==1){
                b = (b+1)/2;
            }else{
                b /=2;
            }
        //    System.out.println("a: "+a+"b: "+b);
            answer++;
            
        }
    }
}