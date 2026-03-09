import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    static int arr[]={0,0,1,7,4,2,0,8};
    static long dp[];

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());
        dp = new long[101];

        Arrays.fill(dp, Long.MAX_VALUE);

        for(int i=2;i<=7;i++){
            dp[i] = arr[i];
            if(arr[i]==0){
                dp[i]=6;
            }
        }

        dp[8] = 10;

        for(int i=9;i<=100;i++){
            for(int j=2;j<=7;j++){
                String number = String.valueOf(dp[i-j]) + String.valueOf(arr[j]);
                dp[i] = Math.min(Long.valueOf(number), dp[i]);
            }
        }
        
        for(int t=0;t<tc;t++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            bw.write(dp[num]+" "+getMaxNum(num)+"\n");
        }

        bw.flush();


    }

    public static String getMaxNum(int n){

        StringBuilder sb = new StringBuilder();
        if(n%2==0){
            for(int j=0;j<n/2;j++){
                sb.append("1");
            }
        }
        else{
            sb.append("7");
            for(int j=0;j<(n-3)/2;j++){
                sb.append("1");
            }
        }

        return sb.toString();
    }

}
