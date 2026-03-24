import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static int N;
    static int M;
    static int H;
    static int W;

    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    static int destY;
    static int destX;
    static int answer = Integer.MAX_VALUE;

    static int sum[][];

    static boolean isVisited[][];


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        isVisited = new boolean[N+1][M+1];
        sum = new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                sum[i][j] = map[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());

        destY = Integer.parseInt(st.nextToken());
        destX = Integer.parseInt(st.nextToken());

        BFS(startY, startX);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
    }

    public static void BFS(int startY, int startX){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX,0});
        isVisited[startY][startX] = true;


        while(!queue.isEmpty()){
            int []current = queue.poll();

            if(current[0]==destY && current[1] == destX){
                answer = Math.min(answer, current[2]);
                continue;
            }

            for(int k=0;k<4;k++){
                int nextY = current[0] + dy[k];
                int nextX = current[1] + dx[k];

                if(isPass(nextY,nextX) && !isVisited[nextY][nextX]){
                    isVisited[nextY][nextX] = true;
                    queue.add(new int[]{nextY,nextX, current[2]+1});
                }

            }
        }
    }

    public static boolean isPass(int startY, int startX){

        int endY = startY + H - 1;
        int endX = startX + W - 1;

        if(startY <= 0 || startX <= 0 || endY > N || endX > M) return false;

        int isHave = sum[endY][endX] - sum[startY-1][endX] - sum[endY][startX-1] + sum[startY-1][startX-1];

        return isHave == 0;
    }



}
