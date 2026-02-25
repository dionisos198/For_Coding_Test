import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int yLimit = 4;
    static int xLimit = 4;
    static int dy[]={0,-1,-1,-1,0,1,1,1};
    static int dx[]={-1,-1,0,1,1,1,0,-1};

    static int sharkY;
    static int sharkX;

    static int smell[][] = new int[yLimit+1][xLimit+1];

    static class Fish{

        int y;
        int x;
        int direction;

        public Fish(int y, int x, int direction){
            this.y = y;
            this.x = x;
            this.direction = direction;
        }

        public void move(){

         //   System.out.println("move 호출");
            for(int i=0;i<=7;i++){

                int newY = y + dy[direction];
                int newX = x + dx[direction];

           //     System.out.println("direction" + direction+"newY: "+newY+"newX: "+newX);
//                if(newY>=1 && newY<=yLimit &&newX>=1 && newX<= xLimit){
//                    System.out.println(smell[newY][newX]);
//                }

                if(newY>=1 && newY<=yLimit && newX>=1 && newX <= xLimit && smell[newY][newX]==0 && !(newY==sharkY && newX==sharkX)){
              //      System.out.println("움직");
                    this.y = newY;
                    this.x = newX;
                    return;
                }

                direction--;
                if(direction < 0){
                    direction = 7;
                }
            }
        }


    }

    static List<Fish> fishList = new ArrayList<>();


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishList.add(new Fish(y,x,direction-1));
        }

        st = new StringTokenizer(br.readLine());

        sharkY = Integer.parseInt(st.nextToken());
        sharkX = Integer.parseInt(st.nextToken());

        for(int i =0;i<S;i++){

            // 복사
            List<Fish> fishCopy = makeCopy(fishList);

//            System.out.println("물고기 이동 전");
//            printSharkAndFish();
            // 물고기 이동
            fishList.stream().forEach(fish-> fish.move());

//            System.out.println("물고기 이동 후 ");
//            printSharkAndFish();

            // 상어 이동
            sharkMove(sharkY, sharkX,0,0,new HashSet<>());

//            System.out.println("상어 이동");
//            for(int []position: finalSharkMark){
//                System.out.println(position[0]+" "+position[1]);
//            }
//            printSharkAndFish();

            catchCount = -1;
            marks.clear();

            List<Fish> saveFishList = new ArrayList<>();
            for(int j=0;j<fishList.size();j++){
                Fish fish = fishList.get(j);
                boolean delete = false;
                for(int position[]: finalSharkMark){
                    if(fish.y == position[0] && fish.x == position[1]){
                        smell[fish.y][fish.x] = 3;
                        delete = true;
                        break;
                    }
                }

                if(!delete){
                    saveFishList.add(fish);
                }

            }

            for(int k=1;k<=yLimit;k++){
                for(int j=1;j<=xLimit;j++){
                    if(smell[k][j]>0){
                        smell[k][j]--;
                    }
                }
            }

            fishList.clear();
            fishList = saveFishList;


            if(!finalSharkMark.isEmpty()){
                sharkY = finalSharkMark.get(finalSharkMark.size()-1)[0];
                sharkX = finalSharkMark.get(finalSharkMark.size()-1)[1];
            }

            finalSharkMark.clear();

            for(Fish fish: fishCopy){
                fishList.add(fish);
            }

//            System.out.println("----------------------");
//            printSharkAndFish();


        }

        System.out.println(fishList.size());




    }

    public static List<Fish> makeCopy(List<Fish> fishList){
        List<Fish> fishes = new ArrayList<>();

        for(int i=0;i<fishList.size();i++){
            fishes.add(new Fish(fishList.get(i).y,fishList.get(i).x,fishList.get(i).direction));
        }

        return fishes;
    }

    static int sharkDY[]={-1,0,1,0};
    static int sharkDX[]={0,-1,0,1};

    static int catchCount = -1;
    static List<int[]> marks = new ArrayList<>();
    static List<int[]> finalSharkMark = new ArrayList<>();
    public static void sharkMove(int sh, int sx, int moveCount, int fishCount, Set<Integer> set) {

//        System.out.println("mark");
//        for(int []position: marks){
//            System.out.println(position[0]+" "+position[1]);
//        }

        if(moveCount == 3 && fishCount>catchCount){
//            System.out.println("fishCount: "+fishCount);
//            System.out.println("catchCount: "+catchCount);
            catchCount = fishCount;
            finalSharkMark.clear();
            for(int[] position: marks){
                finalSharkMark.add(new int[]{position[0],position[1]});
            }
          //  System.out.println("여기 안오는거지?");
            return;
        }
        else if(moveCount >= 3){
            return;
        }

        for(int i=0;i<4;i++){
            int newsh = sh + sharkDY[i];
            int newsx = sx + sharkDX[i];


            if(newsh>=1 && newsh<=yLimit && newsx>=1 && newsx<=xLimit){
                marks.add(new int[]{newsh, newsx});
                int count = 0;
                for(Fish fish: fishList){
                    if(fish.y == newsh && fish.x ==newsx && !set.contains(10*newsh+newsx)){
                        count++;
                    }
                }

                boolean added = set.add(10*newsh+newsx);
                sharkMove(newsh,newsx, moveCount+1, fishCount+count,set);
                marks.remove(marks.size()-1);
                if(added) set.remove(10*newsh+newsx);
            }


        }
    }

    public static void printSharkAndFish(){
        System.out.println("sharkY: "+sharkY +" "+"sharkX: "+sharkX);
        for(int j=0;j<fishList.size();j++){
            Fish fish = fishList.get(j);
            System.out.println(fish.y+" "+fish.x+" "+fish.direction);
        }
    }

}
