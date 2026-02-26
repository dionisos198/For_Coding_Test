import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 정답 코드 참조
     * 처음에 생각은 그냥 뒤에서 부터 숫자를 보고 증가하는 순열과 감소하는 순열을 파악하는 문제라고 생각함.
     * 근데 앞에서 부터 돌리는 정답 코드를 보니, 이걸 나중에 어케 생각할까 생각하게 됨 -> 진짜 어케 생각하냐?
     * 정답 보고 하면 이해는 되는데 거기까지 어케 도달하지.
     *
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String sentence = st.nextToken();
        int countOfPop = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<sentence.length();i++){
            while(!stack.isEmpty() && stack.peek() < sentence.charAt(i)-'0' && countOfPop < K){
                stack.pop();
                countOfPop++;
            }

            stack.add(sentence.charAt(i)-'0');
        }

        while(countOfPop < K){
            stack.pop();
            countOfPop++;
        }


        StringBuilder answer = new StringBuilder();
        while(!stack.isEmpty()){
            answer.insert(0, String.valueOf(stack.pop()));
        }

        System.out.println(answer.toString());


    }

}
