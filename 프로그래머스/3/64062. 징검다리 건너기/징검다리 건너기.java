class Solution {
    public int solution(int[] stones, int k) {
        int lo = 1, hi = 200000000, ans = 0;
        while (lo <= hi) {
            int mid = (lo+hi)/2;
            if (canCross(stones, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean canCross(int[] stones, int k, int people) {
        int cnt = 0;
        for (int s : stones) {
            if (s < people) {
                if (++cnt >= k) return false;   // 연속 k개 → 못 건넘
            } else {
                cnt = 0;
            }
        }
        return true;
    }
}