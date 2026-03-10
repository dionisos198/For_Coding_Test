import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(st.nextToken());

        for(int t=0;t<tc;t++){
            st = new StringTokenizer(br.readLine());
            // 메모리 배열 크기
            int sm = Integer.parseInt(st.nextToken());
            //프로그램 코드 길이
            int sc = Integer.parseInt(st.nextToken());
            //입력의 길이
            int si = Integer.parseInt(st.nextToken());

            int arr[] = new int[sm];
            int arrPointer = 0;
            int commandPointer = 0;
            int inputPointer = 0;

            st = new StringTokenizer(br.readLine());
            String commands = st.nextToken();

            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            Map<Integer,Integer> map = new HashMap<>();

            Stack<Integer> stack = new Stack<>();
            for(int i=0;i<sc;i++){
                if(commands.charAt(i)=='['){
                    stack.add(i);
                }
                else if(commands.charAt(i)==']'){
                    int beforeStack = stack.peek();
                    map.put(beforeStack, i);
                    map.put(i,beforeStack);
                    stack.pop();
                }
            }

            int count = 0;
            boolean loop = true;
            int maxCommandPointer = 0;
            while(count<=100000000){

                if(commandPointer == sc){
                    loop = false;
                    break;
                }
                if(count>=50000000){
                    maxCommandPointer = Math.max(commandPointer, maxCommandPointer);
                }
                
                if(commands.charAt(commandPointer)=='+'){
                    arr[arrPointer]+=1;
                    arr[arrPointer] %= 256;
                }
                else if(commands.charAt(commandPointer)=='-'){
                    arr[arrPointer]-=1;
                    if(arr[arrPointer]==-1){
                        arr[arrPointer] = 255;
                    }
                }
                else if(commands.charAt(commandPointer)=='<'){
                    arrPointer--;
                    if(arrPointer==-1){
                        arrPointer = sm-1;
                    }
                }
                else if(commands.charAt(commandPointer)=='>'){
                    arrPointer++;
                    if(arrPointer==sm){
                        arrPointer = 0;
                    }
                }
                else if(commands.charAt(commandPointer)=='[' && arr[arrPointer]==0){
                    commandPointer = map.get(commandPointer);
                }
                else if(commands.charAt(commandPointer)==']' && arr[arrPointer]!=0){
                    commandPointer = map.get(commandPointer);
                }
                else if(commands.charAt(commandPointer)==','){
                    if(inputPointer == si){
                        arr[arrPointer] = 255;
                    }
                    else{
                        arr[arrPointer] = input.charAt(inputPointer);
                        inputPointer++;
                    }
                }

                commandPointer++;
                count++;

            }

            if(loop){
                bw.write("Loops"+" "+map.get(maxCommandPointer)+" "+maxCommandPointer+"\n");
            }
            else{
                bw.write("Terminates"+"\n");
            }


        }

        bw.flush();
    }

}
