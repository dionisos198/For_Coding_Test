class Solution {
    public int solution(int[][] board) {
        int n = board.length, m = board[0].length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) continue;
                if (i > 0 && j > 0) {
                    // i,j를 우 하단으로 하는 정사각형 최대 한변의 길이
                    board[i][j] = Math.min(board[i-1][j-1],
                                  Math.min(board[i-1][j], board[i][j-1])) + 1;
                }
                max = Math.max(max, board[i][j]);
            }
        }
        return max * max;
    }
}