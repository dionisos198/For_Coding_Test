import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];
        long dp[][] = new long[N][21];


        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][arr[0]] = 1;
        for(int i=1;i<N-1;i++){
            for(int j=0;j<=20;j++){
                if(dp[i-1][j]!=0){
                    int tmpPlus = j + arr[i];
                    int tmpMinus = j - arr[i];

                    if(tmpPlus>=0 && tmpPlus<=20){
                        dp[i][tmpPlus] += dp[i-1][j];
                    }
                    if(tmpMinus>=0 && tmpMinus<=20){
                        dp[i][tmpMinus] += dp[i-1][j];
                    }
                }
            }
        }

        System.out.println(dp[N-2][arr[N-1]]);



    }

}
