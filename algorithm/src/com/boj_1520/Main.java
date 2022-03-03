package com.boj_1520;

import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][] dp;
    static int[][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int DFS(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }

        if (dp[x][y] > 0 || ch[x][y] == 1) {
            return dp[x][y];
        }

        ch[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] < board[x][y]) {
                dp[x][y] += DFS(nx, ny);
            }
        }

        return dp[x][y];
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];
        dp = new int[n][m];
        ch = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        T.DFS(0, 0);
        System.out.println(dp[0][0]);
    }
}