class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        for(int i = 0; i <= stations.length; i++) {
            int len = 0;
            
            if(i == 0) {
                len = stations[i] - 1 - w;
            } else if(i == stations.length) {
                len = n - stations[i-1] - w;
            } else {
                len = stations[i] - stations[i-1] - (w * 2) -1;
            }
            
            //빈공간 길이가 0보다 크면 기지국 설치
            if(len >= 0) {
                int count = len / (w * 2 + 1);
                int remain = len % (w * 2 + 1);
                answer += count;
                if(remain != 0) answer++;
            }
        }
        return answer;
    }
}