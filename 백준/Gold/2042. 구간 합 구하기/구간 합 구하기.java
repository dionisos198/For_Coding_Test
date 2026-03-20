import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static long arr[];
    static long segmentTree[];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 구간
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수.

        arr = new long[N+1];
        segmentTree = new long[N*4];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1,1,N);

        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(order == 1){
                update(1,1,N,b,c);
            }
            else if(order == 2){
                bw.write(querySum(1,1,N,b,(int)c)+"\n");
            }
        }


        bw.flush();









    }

    //node를 tree에서의 index
    public static void init(int node, int left, int right){

        // 같을 떄
        if(left == right){
            segmentTree[node] = arr[left];
            return;
        }

        int mid = (left+right)/2;
        init(node * 2,left,mid);
        init(node * 2 + 1,mid+1,right);

        segmentTree[node] = segmentTree[node*2] + segmentTree[node * 2 +1];
    }

    // 해당 노드 값을 먼저 업데이트 하고 나머지 segment를 업데이트?
    private static void update(int node, int left, int right, int queryIndex, long value) {
        if (queryIndex < left || right < queryIndex) {
            return;
        }

        if (left == right) {
            segmentTree[node] = value;
            return;
        }

        int mid = (left + right) / 2;

        update(node * 2, left, mid, queryIndex, value);
        update(node * 2 + 1, mid + 1, right, queryIndex, value);
        segmentTree[node] = segmentTree[node *2] + segmentTree[node*2 + 1];
    }

    // 합 구하기
    private static long querySum(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
        if (queryRight < nodeLeft || queryLeft > nodeRight) {
            return 0;
        }

        if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
            return segmentTree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;

        long leftSum = querySum(node * 2, nodeLeft, mid, queryLeft, queryRight);
        long rightSum = querySum(node * 2+ 1, mid + 1, nodeRight, queryLeft, queryRight);
        return leftSum + rightSum;
    }






}
