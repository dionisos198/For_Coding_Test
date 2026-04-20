import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    static int graph[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];

        int reward[] = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++){
            reward[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i==j){
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[a][b] = l;
            graph[b][a] = l;
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(graph[i][k]==Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    if(graph[i][j]>graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=n;j++){
//                System.out.print(graph[i][j]+" ");
//            }
//            System.out.println();
//        }


        int max = -1;

        for(int start=1;start<=n;start++){
            int sum = 0;
            for(int j=1;j<=n;j++){
                if(graph[start][j]<=m){
                    sum += reward[j];
                }
            }

            max = Math.max(sum,max);
        }

        System.out.println(max);


    }

}
