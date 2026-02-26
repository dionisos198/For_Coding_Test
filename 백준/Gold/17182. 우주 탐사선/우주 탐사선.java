import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int [][]graph;
    static boolean isVisited[];

    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        isVisited = new boolean[N];


        for(int i=0;i<N;i++){
           st = new StringTokenizer(br.readLine());
           for(int j=0;j<N;j++){
               graph[i][j] = Integer.parseInt(st.nextToken());
           }
        }

        floydInit(N);
        BT(0, N, 0, new boolean[N+1], K);

        System.out.println(answer);





    }

    public static void floydInit(int N){

        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(graph[i][j]>graph[i][k]+graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    public static void BT(int count, int N, int cost,boolean isVisited[], int currentNumber){

        if(count == N){
            answer = Math.min(answer,cost);
            return;
        }

        for(int i=0;i<N;i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                BT(count+1,N,cost + graph[currentNumber][i], isVisited, i);
                isVisited[i]= false;
            }
        }

    }



}
