class Solution {
    public int[] solution(int n, int s) {
        if (s < n) return new int[]{-1};

        int q = s / n, r = s % n;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (i < n - r) ? q : q + 1;   // 오름차순 자동 정렬
        }
        return answer;
    }
}
