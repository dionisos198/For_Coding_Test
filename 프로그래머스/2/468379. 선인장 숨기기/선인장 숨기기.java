class Solution {
    int m, n, h, w;
    int[][] drops;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        this.m = m; this.n = n; this.h = h; this.w = w;
        this.drops = drops;

        int lo = 0, hi = drops.length + 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (hasSafeZone(mid)) lo = mid + 1; 
            else hi = mid;                     
        }


        if (lo == drops.length + 1) {
            int[][] psum = buildPsum(drops.length);
            return findFirst(psum, -1, -1);
        }

        int[][] psum = buildPsum(lo - 1);
        int dr = drops[lo - 1][0], dc = drops[lo - 1][1];
        return findFirst(psum, dr, dc);
    }

    int[] findFirst(int[][] psum, int dr, int dc) {
        for (int r = 0; r <= m - h; r++) {
            for (int c = 0; c <= n - w; c++) {
                if (queryPsum(psum, r, c, r + h - 1, c + w - 1) == 0) {
                    if (dr == -1) return new int[]{r, c};
                    if (r <= dr && dr <= r + h - 1 && c <= dc && dc <= c + w - 1)
                        return new int[]{r, c};
                }
            }
        }
        return new int[]{0, 0};
    }

    boolean hasSafeZone(int k) {
        int[][] psum = buildPsum(k);
        for (int r = 0; r <= m - h; r++)
            for (int c = 0; c <= n - w; c++)
                if (queryPsum(psum, r, c, r + h - 1, c + w - 1) == 0) return true;
        return false;
    }

    int[][] buildPsum(int k) {
    
        int[][] grid = new int[m][n];
        for (int i = 0; i < k; i++)
            grid[drops[i][0]][drops[i][1]] = 1;

        int[][] psum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                psum[i][j] = grid[i-1][j-1]
                           + psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1];
        return psum;
    }

    int queryPsum(int[][] psum, int r1, int c1, int r2, int c2) {
        return psum[r2+1][c2+1] - psum[r1][c2+1]
             - psum[r2+1][c1]   + psum[r1][c1];
    }
}