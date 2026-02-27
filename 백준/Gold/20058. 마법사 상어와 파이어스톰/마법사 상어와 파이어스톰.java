import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int map[][];

    /**
     틀린이유: 몇 가지 예외 나왔을 때 틀린 이유 ->
     동시에 녹는 것을 고려하지 못함. 명시 안되어 있으니 그냥 하던 대로 넘어가 버림 
     시계 방향으로 돌리는 것을 너무 복잡하게 생각. 
     **/

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        map = new int[(int)Math.pow(2,N)][(int)Math.pow(2,N)];

        for(int i=0;i<map.length;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<map[0].length;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){

            int dem = (int)Math.pow(2,Integer.parseInt(st.nextToken()));
            turnMap(dem);
            melt();
        }

        System.out.println(sumOfIce());
        System.out.println(getMostChunkSize());


    }

    public static int sumOfIce(){

        int sum = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                sum += map[i][j];
            }
        }

        return sum;
    }

    public static int getMostChunkSize(){

        boolean isVisited[][] = new boolean[map.length][map[0].length];
        int maxSize = 0;

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(!isVisited[i][j] && map[i][j]>0){
                    isVisited[i][j] = true;
                    maxSize = Math.max(maxSize, chunkSize(i,j,isVisited));
                }
            }
        }

        return maxSize;
    }

    static int dy[]={1,0,-1,0};
    static int dx[]={0,1,0,-1};

    public static int chunkSize(int i,int j,boolean isVisited[][]){

        int size = 1;

        for(int k=0;k<4;k++){
            int newY = i + dy[k];
            int newX = j + dx[k];

            if(!outRange(newY,newX) && !isVisited[newY][newX] && map[newY][newX]>0){
                isVisited[newY][newX] = true;
                size += chunkSize(newY,newX,isVisited);
            }
        }

        return size;

    }

    public static void turnMap(int dem){

        for(int i=0;i<map.length;i+=dem){
            for(int j=0;j<map[0].length;j+=dem){
                turnDem(i, j, dem);
            }
        }
    }

    public static void turnDem(int startY, int startX, int dem){

        int tmpMap[][] = new int[dem][dem];

        for(int i=0;i<dem;i++){
            for(int j=0;j<dem;j++){
                tmpMap[j][dem-1-i] = map[startY+i][startX+j];
            }
        }

        for(int i=0;i<dem;i++){
            for(int j=0;j<dem;j++){
                map[startY+i][startX+j] = tmpMap[i][j];
            }
        }

    }

    public static void melt(){

        List<int[]> meltList = new ArrayList<>();

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                int countOfJub = 0;

                for(int k=0;k<4;k++){
                    if(!outRange(i+dy[k],j+dx[k]) && map[i+dy[k]][j+dx[k]]>=1) countOfJub++;
                }

                if(countOfJub<3 && map[i][j]>0){
                    meltList.add(new int[]{i,j});
                }
            }
        }

        for(int []melt: meltList){
            map[melt[0]][melt[1]]--;
        }
    }

    public static boolean outRange(int i,int j){
        return i<0 || i>=map.length || j<0 || j>=map[0].length;
    }

    public static void printMap(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
            }

            System.out.println();
        }
    }

}
