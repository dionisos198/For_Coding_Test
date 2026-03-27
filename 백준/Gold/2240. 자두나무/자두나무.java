import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int arr[] = new int[T+1];
        int dp[][][] = new int[T+1][W+1][3];

        for(int i=1;i<=T;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=T;i++){

            dp[i][0][1] = dp[i-1][0][1];
            if(arr[i] == 1) dp[i][0][1]++;

            for(int j=1;j<=W;j++){

                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]);
                dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]);
                dp[i][j][arr[i]]++;
            }
        }

        int answer = 0;
        for(int i=1;i<=W;i++){
            answer = Math.max(dp[T][W][1],Math.max(dp[T][W][2],answer));
        }



        System.out.println(answer);






    }

}
