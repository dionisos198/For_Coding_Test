import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;

    static boolean success = false;

    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    static int holeY;
    static int holeX;



    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int redY = 0;
        int redX = 0;
        int blueY = 0;
        int blueX = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String sentence = st.nextToken();
            for(int j=0;j<M;j++){
                map[i][j] = sentence.charAt(j);
                if(map[i][j]=='R'){
                    redY = i;
                    redX = j;
                }
                else if(map[i][j]=='B'){
                    blueY = i;
                    blueX = j;
                }
                else if(map[i][j]=='O'){
                    holeY = i;
                    holeX = j;
                }
            }
        }

        BT(0,redY, redX, blueY, blueX);

        if(success){
            System.out.println("1");
        }
        else{
            System.out.println("0");
        }





    }


    public static void BT(int count, int redY, int redX, int blueY, int blueX){


        if(success){
            return;
        }

        if(count>=10){
            return;
        }

        if(map[blueY][blueX]=='O'){
            return;
        }

        if(map[redY][redX]=='O'){
            success = true;
            return;
        }

        for(int k=0;k<4;k++){

            int newRedInfo[] = move(redY, redX, k);
            int newBlueInfo[] = move(blueY, blueX, k);


            if(newRedInfo[0]==newBlueInfo[0] && newRedInfo[1]==newBlueInfo[1] &&
               !(newRedInfo[0]==holeY && newRedInfo[1]==holeX)){
                if(newRedInfo[2]<newBlueInfo[2]){
                    newBlueInfo[0]-=dy[k];
                    newBlueInfo[1]-=dx[k];
                }
                else{
                    newRedInfo[0]-=dy[k];
                    newRedInfo[1]-=dx[k];
                }
            }

//            System.out.println("k: "+k);
//            System.out.println("oldR: "+redY +" "+redX);
//            System.out.println("oldB: "+blueY+" "+blueX);
//            System.out.println("newR: "+newRedInfo[0]+" "+newRedInfo[1]);
//            System.out.println("newB: "+newBlueInfo[0]+" "+newBlueInfo[1]);

            if(isSamePosition(redY, redX, newRedInfo[0],newRedInfo[1]) &&
               isSamePosition(blueY, blueX, newBlueInfo[0], newBlueInfo[1])){
                continue;
            }

            if(isSamePosition(blueY, blueX, newBlueInfo[0], newBlueInfo[1])){
                if(isInPath(redY,redX,newRedInfo[0],newRedInfo[1])){
                    success = true;
                    continue;
                }
                BT(count+1, newRedInfo[0],newRedInfo[1], blueY, blueX);
            }
            else if(isSamePosition(redY, redX, newRedInfo[0], newRedInfo[1])){
                if(isInPath(blueY,blueX,newBlueInfo[0], newBlueInfo[1])){
                    continue;
                }
                BT(count+1,redY, redX, newBlueInfo[0], newBlueInfo[1]);
            }
            else{
                if(isInPath(redY,redX,newRedInfo[0],newRedInfo[1]) && !isInPath(blueY, blueX, newBlueInfo[0], newBlueInfo[1])){
                    success = true;
                    continue;
                }
                if(isInPath(blueY, blueX, newBlueInfo[0], newBlueInfo[1])){
                    continue;
                }
                BT(count+1, newRedInfo[0], newRedInfo[1], newBlueInfo[0], newBlueInfo[1]);
            }



        }

    }

    public static boolean isSamePosition(int y, int x, int newY, int newX){

        if(y == newY && x == newX){
            return true;
        }

        return false;

    }

    public static boolean isInPath(int redY, int redX, int newRedY, int newRedX){

        if(redY == newRedY){
            for(int i=Math.min(redX, newRedX);i<=Math.max(redX,newRedX);i++){
                if(holeY ==redY && holeX == i){
                    return true;
                }
            }
        }

        if(redX == newRedX){
            for(int i=Math.min(redY,newRedY);i<=Math.max(redY,newRedY);i++){
                if(holeX == redX && holeY == i){
                    return true;
                }
            }
        }

        return false;
    }

    public static int[] move(int y, int x, int k){

        int newY = y;
        int newX = x;
        int count = 0;


        while(true){
            if(map[newY][newX]=='#'){
                if(newY == y && newX == x){
                    return new int[]{y,x,count};
                }
                return new int[]{newY - dy[k],newX - dx[k],count-1};
            }
            newY = newY + dy[k];
            newX = newX + dx[k];
            count++;
        }

    }
}
