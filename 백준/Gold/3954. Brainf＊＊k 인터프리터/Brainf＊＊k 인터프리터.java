import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine()); // 첫 줄 테스트 케이스 수

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());

            int arr[] = new int[sm];
            int arrPointer = 0;
            int commandPointer = 0;
            int inputPointer = 0;

            String commands = br.readLine();
            String input = br.readLine();

            Map<Integer, Integer> map = new HashMap<>();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < sc; i++) {
                if (commands.charAt(i) == '[') {
                    stack.add(i);
                } else if (commands.charAt(i) == ']') {
                    int beforeStack = stack.pop();
                    map.put(beforeStack, i);
                    map.put(i, beforeStack);
                }
            }

            int count = 0;
            int maxCommandPointer = 0; // 무한 루프 확정 후 가장 뒤에 있는 ']'의 위치

            // 핵심 수정: 5,000만 번까지는 그냥 수행, 그 후 5,000만 번 동안 가장 우측의 ] 기록
            while (count <= 100000000) {
                if (commandPointer == sc) break;

                // 무한 루프 구간(5,000만 번 초과)에 진입했을 때만 maxCommandPointer 갱신
                if (count > 50000000) {
                    maxCommandPointer = Math.max(commandPointer, maxCommandPointer);
                }

                char cmd = commands.charAt(commandPointer);

                if (cmd == '+') {
                    arr[arrPointer] = (arr[arrPointer] + 1) % 256;
                } else if (cmd == '-') {
                    arr[arrPointer] = (arr[arrPointer] - 1 + 256) % 256;
                } else if (cmd == '<') {
                    arrPointer = (arrPointer - 1 + sm) % sm;
                } else if (cmd == '>') {
                    arrPointer = (arrPointer + 1) % sm;
                } else if (cmd == '[' && arr[arrPointer] == 0) {
                    commandPointer = map.get(commandPointer);
                } else if (cmd == ']' && arr[arrPointer] != 0) {
                    // 루프를 돌기 위해 뒤로 돌아갈 때도 max index 체크 가능하지만,
                    // 위에서 이미 체크하므로 로직 유지
                    commandPointer = map.get(commandPointer);
                } else if (cmd == ',') {
                    if (inputPointer == si) {
                        arr[arrPointer] = 255;
                    } else {
                        arr[arrPointer] = input.charAt(inputPointer++);
                    }
                }

                commandPointer++;
                count++;
            }

            if (commandPointer == sc) {
                bw.write("Terminates\n");
            } else {
                // maxCommandPointer는 무한 루프 중인 ']' 중 가장 뒤의 것
                bw.write("Loops " + map.get(maxCommandPointer) + " " + maxCommandPointer + "\n");
            }
        }
        bw.flush();
    }
}