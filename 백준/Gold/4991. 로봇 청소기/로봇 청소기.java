import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        while(true){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w ==0 && h==0){
                break;
            }

            char map[][] = new char[h][w];
            int count = 0;
            int currentY = 0;
            int currentX = 0;
            int answer = 0;
            List<int[]> check = new ArrayList<>();

            for(int i=0;i<h;i++){
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                for(int j=0;j<w;j++){
                    map[i][j] = s.charAt(j);
                    if(map[i][j]=='*'){
                        check.add(new int[]{i,j});
                        count++;
                    }
                    else if(map[i][j]=='o'){
                        currentY = i;
                        currentX = j;
                        count++;
                    }
                }
            }

            int graph[][] = new int[count][count];
            for(int i=0;i<count;i++){
                for(int j=0;j<count;j++){
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }

            check.add(0,new int[]{currentY,currentX});


            for(int i=0; i<check.size(); i++){
                int[] start = check.get(i);

                List<int[]> result = BFS(map,h,w,start[0], start[1]);

                for(int[] r : result){
                    for(int j=0; j<check.size(); j++){
                        if(check.get(j)[0] == r[0] && check.get(j)[1] == r[1]){
                            graph[i][j] = r[2];
                            graph[j][i] = r[2];
                        }
                    }
                }
            }

            boolean getOut = false;

            for(int i=0;i<count;i++){
                for(int j=0;j<count;j++){
                    if(i!=j && graph[i][j] == Integer.MAX_VALUE){
                        getOut = true;
                        break;
                    }
                }
                if(getOut){
                    break;
                }
            }

            if(getOut){
                bw.write(-1+"\n");
                continue;
            }

            answer = getAnswer(graph, 0, count);

            if(answer ==Integer.MAX_VALUE){
                bw.write(-1+"\n");
            }
            else{
                bw.write(answer+"\n");
            }
        }

        bw.flush();
    }
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    static int min;
    static boolean[] visited;

    public static int getAnswer(int[][] graph, int startP, int count){

        visited = new boolean[count];
        min = Integer.MAX_VALUE;

        visited[startP] = true;

        dfs(graph, startP, count, 0, 0);

        return min;
    }

    public static void dfs(int[][] graph, int now, int count, int depth, int sum){

        if(sum >= min) return;

        if(depth == count - 1){
            min = Math.min(min, sum);
            return;
        }

        for(int next = 0; next < count; next++){

            if(visited[next]) continue;

            if(graph[now][next] == Integer.MAX_VALUE) continue;

            visited[next] = true;
            dfs(graph, next, count, depth + 1, sum + graph[now][next]);
            visited[next] = false;
        }
    }

    public static List<int[]> BFS(char map[][], int h, int w, int currentY, int currentX){
        Queue<int[]> queue = new LinkedList<>();
        boolean isVisited[][] = new boolean[h][w];
        List<int[]> list = new ArrayList<>();

        queue.add(new int[]{currentY,currentX,0});
        isVisited[currentY][currentX] = true;

        while(!queue.isEmpty()){
            int []current = queue.poll();

            if(map[current[0]][current[1]] == '*'){
                list.add(new int[]{current[0],current[1],current[2]});
            }

            for(int k=0;k<4;k++){
                int nextY = current[0] + dy[k];
                int nextX = current[1] + dx[k];

                if(nextY>=0 && nextY<h && nextX>=0 &&nextX<w && map[nextY][nextX]!='x' && !isVisited[nextY][nextX]){
                    queue.add(new int[]{nextY,nextX,current[2]+1});
                    isVisited[nextY][nextX] = true;
                }

            }

        }

     //   System.out.println("changeY: "+changeY+"changeX: "+changeX+"moveCount: "+moveCount);

        return list;

    }

}
