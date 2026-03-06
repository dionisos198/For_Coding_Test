import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int height[] = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            height[i] = Integer.parseInt(st.nextToken());
        }


        int nearNum[] = new int[N+1];
        int count[] = new int[N+1];

        Stack<Integer> stack = new Stack<>();
        //왼쪽부터
        for(int i=1;i<=N;i++){
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]){
                stack.pop();
            }

            count[i] += stack.size();
            if(count[i]>0){
                nearNum[i] = stack.peek();
            }

            stack.add(i);
        }

        stack.clear();

        // 오른쪽
        for(int i=N;i>=1;i--){
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]){
                stack.pop();
            }

            int increase = stack.size();
            if(increase>0){
                if(count[i]==0){
                    nearNum[i] = stack.peek();
                }
                else{
                    if(stack.peek()-i<i-nearNum[i]){
                        nearNum[i] = stack.peek();
                    }
                }
                count[i] += increase;
            }

            stack.add(i);
        }


        for(int i=1;i<=N;i++){
            System.out.print(count[i]+" ");
            if(count[i]>0){
                System.out.print(nearNum[i]);
            }
            System.out.println();

        }
    }

}
