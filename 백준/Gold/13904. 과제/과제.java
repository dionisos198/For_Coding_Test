import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Task implements Comparable<Task>{
        int day;
        int reward;

        public Task(int day, int reward){
            this.day = day;
            this.reward = reward;
        }

        @Override
        public int compareTo(Task o) {
            if(this.reward == o.reward){
                return this.day - o.day;
            }

            return o.reward - this.reward;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int day[] = new int[1001];

        PriorityQueue<Task> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            pq.add(new Task(d,reward));
        }

        while(!pq.isEmpty()){
            Task cur = pq.poll();

            for(int i=cur.day;i>=1;i--){
                if(day[i]==0){
                    day[i] = cur.reward;
                    break;
                }
            }
        }

        int answer = 0;
        for(int i=1;i<=1000;i++){
            answer += day[i];
        }

        System.out.println(answer);


    }

}
