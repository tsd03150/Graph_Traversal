package com.boj_1937;

import java.util.Scanner;

class Main {
    static int n;
    static int[][] board;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int DFS(int x, int y) {
        if (dp[x][y] > 1) {
            return dp[x][y];
        }

        int max = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (board[x][y] < board[nx][ny]) {
                    max = Math.max(max, DFS(nx, ny) + 1);
                }
            }
        }
        dp[x][y] = max;
        return dp[x][y];
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, T.DFS(i, j));
            }
        }

        System.out.println(answer);
    }
}
