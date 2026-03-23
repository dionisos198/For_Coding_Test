import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int n;

    static int SIZE = 1000000;
    static SegTree segmentTree = new SegTree(4 * SIZE);

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

   //     segmentTree.init(1, 1, SIZE);

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int b = Integer.parseInt(st.nextToken());
                int res = binSearch(1, SIZE, b);
                bw.write(res + "\n");
                segmentTree.update(1, 1, SIZE, res, -1);
            }

            if (op == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                segmentTree.update(1, 1, SIZE, b, c);
            }

        }
        bw.flush();
    }

    public static int binSearch(int left, int right, int b) {


        do {

            int mid = (left + right) / 2;
            long sum = segmentTree.sum(1, 1, SIZE, 1, mid);

            if (sum < b)
                left = mid + 1;
            else if (sum >= b)
                right = mid - 1;

        } while (left <= right);

        return left;
    }

    static class SegTree {

        long[] tree;

        SegTree(int n) {
            tree = new long[n];
        }

        public long init(int node, int start, int end) {

            if (start == end)
                return tree[node] = 0;

            int mid = (start + end) / 2;

            tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);

            return tree[node];

        }

        //b: 넣을 사탕의 맛
        //c: 그 맛의 갯수
        // node는 범위
        public void update(int node, int start, int end, int b, int c) {

            if (b < start || end < b)
                return;

            tree[node] += c;

            if (start == end)
                return;

            int mid = (start + end) / 2;

            update(node * 2, start, mid, b, c);
            update(node * 2 + 1, mid + 1, end, b, c);
        }

        public long sum(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {

            if (queryLeft > nodeRight || queryRight < nodeLeft)
                return 0;

            if (queryLeft <= nodeLeft && nodeRight <= queryRight)
                return tree[node];

            int mid = (nodeLeft + nodeRight) / 2;

            return sum(node * 2, nodeLeft, mid, queryLeft, queryRight)
                    + sum(node * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight);

        }
    }

}
