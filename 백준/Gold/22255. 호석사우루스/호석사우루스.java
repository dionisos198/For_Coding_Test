import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static boolean isVisited[][][];

    static int N;
    static int M;

    static int dist[][][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());

        int endY = Integer.parseInt(st.nextToken());
        int endX = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        isVisited = new boolean[N+1][M+1][3];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[N+1][M+1][3];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                for(int k=0;k<3;k++){
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        BFS2(startY,startX, endY, endX);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    static int answer = Integer.MAX_VALUE;

    public static void BFS(int startY, int startX, int endY, int endX){

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startY, startX, 0, 1});
        isVisited[startY][startX][0] = true;

        while(!queue.isEmpty()){
            int current[] = queue.poll();

            System.out.println(current[0]+" "+current[1]+" "+current[2]+" "+current[3]);

            if(current[0]==endY && current[1]==endX){
                answer = Math.min(current[2], answer);
                continue;
            }

            if(current[3]%3==0){
                for(int k=0;k<4;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M && !isVisited[nextY][nextX][(current[3]+1)%3]
                       && map[nextY][nextX]!=-1){
                        isVisited[nextY][nextX][(current[3]+1)%3]=true;
                        queue.add(new int[]{nextY,nextX,current[2]+map[nextY][nextX],(current[3]+1)});
                    }
                }
            }
            else if(current[3]%3==1){
                for(int k=0;k<2;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M && !isVisited[nextY][nextX][(current[3]+1)%3]
                        && map[nextY][nextX]!=-1){
                        isVisited[nextY][nextX][(current[3]+1)%3]=true;
                        queue.add(new int[]{nextY,nextX,current[2]+map[nextY][nextX],(current[3]+1)});
                    }
                }
            }
            else if(current[3]%3==2){
                for(int k=2;k<4;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M && !isVisited[nextY][nextX][(current[3]+1)%3]
                        && map[nextY][nextX]!=-1){
                        isVisited[nextY][nextX][(current[3]+1)%3]=true;
                        queue.add(new int[]{nextY,nextX,current[2]+map[nextY][nextX],(current[3]+1)});
                    }
                }
            }
        }


    }

    public static void BFS2(int startY, int startX, int endY, int endX){

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startY,startX,1,0});
        dist[startY][startX][1] = 0;

        while(!queue.isEmpty()){
            int current[] = queue.poll();

            if(dist[current[0]][current[1]][current[2]] < current[3]){
                continue;
            }

            if(current[0]==endY && current[1]==endX){
                answer = Math.min(current[3], answer);
                continue;
            }

            if(current[2]%3==0){
                for(int k=0;k<4;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M &&
                            dist[nextY][nextX][(current[2]+1)%3] > current[3] + map[nextY][nextX]
                            && map[nextY][nextX]!=-1){

                        dist[nextY][nextX][(current[2]+1)%3] = current[3] + map[nextY][nextX];
                        queue.add(new int[]{nextY,nextX,(current[2]+1)%3,dist[nextY][nextX][(current[2]+1)%3]});
                    }
                }
            }
            else if(current[2]%3==1){
                for(int k=0;k<2;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M &&
                            dist[nextY][nextX][(current[2]+1)%3] > current[3] + map[nextY][nextX]
                            && map[nextY][nextX]!=-1){
                        dist[nextY][nextX][(current[2]+1)%3] = current[3] + map[nextY][nextX];
                        queue.add(new int[]{nextY,nextX,(current[2]+1)%3,dist[nextY][nextX][(current[2]+1)%3]});
                    }
                }
            }
            else if(current[2]%3==2){
                for(int k=2;k<4;k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX>=1 && nextX<=M &&
                            dist[nextY][nextX][(current[2]+1)%3] > current[3] + map[nextY][nextX]
                            && map[nextY][nextX]!=-1){
                        dist[nextY][nextX][(current[2]+1)%3] = current[3] + map[nextY][nextX];
                        queue.add(new int[]{nextY,nextX,(current[2]+1)%3,dist[nextY][nextX][(current[2]+1)%3]});
                    }
                }
            }
        }
    }

}
