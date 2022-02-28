package com.boj_2468;

import java.util.Scanner;

class Main {
    static int n;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void DFS(int x, int y, int k, int[][] ch) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (board[nx][ny] > k && ch[nx][ny] != 1) {
                    ch[nx][ny] = 1;
                    DFS(nx, ny, k, ch);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new int[n][n];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
                max = Math.max(max, board[i][j]);
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int k = 0; k <= max; k++) {
            int tmp = 0;
            int[][] ch = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] > k && ch[i][j] != 1) {
                        tmp++;
                        ch[i][j] = 1;
                        T.DFS(i, j, k, ch);
                    }
                }
            }
            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);
    }
}
