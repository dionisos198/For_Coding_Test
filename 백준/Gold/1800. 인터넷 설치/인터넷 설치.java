import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static List<Node>[] graph;
    static int N, P, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost));

            maxCost = Math.max(maxCost, cost);
        }

        int left = 0;
        int right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }


    // limit -> 지불 비용
    static boolean dijkstra(int limit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 현재노드, limit 보다 비싼 간선의 갯수
        pq.add(new int[]{1, 0});
        //limit 보다 비싼 간선의 최소 개수
        dist[1] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int expensiveCount = now[1];

            if (node == N) return true;

            if (dist[node] < expensiveCount) continue;

            for (Node next : graph[node]) {
                int nextCount = expensiveCount;

                // limit보다 크면 비싼 간선
                if (next.cost > limit) nextCount++;

                // 기존보다 더 적은 limit 보다 큰 간선의 개수가 있으면 업데이트
                if (nextCount < dist[next.dest] && nextCount <= K) {
                    dist[next.dest] = nextCount;
                    pq.add(new int[]{next.dest, nextCount});
                }
            }
        }

        return false;
    }
}
