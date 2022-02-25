package com.boj_4963;

import java.util.Scanner;

class Main {
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public void DFS(int x, int y, int n, int m) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] == 1) {
                board[nx][ny] = 0;
                DFS(nx, ny, n, m);
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        while (true) {
            int m = kb.nextInt();
            int n = kb.nextInt();

            if (n == 0 && m == 0) {
                System.out.println(answer);
                return;
            }

            board = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = kb.nextInt();
                }
            }

            int tmp = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        tmp++;
                        board[i][j] = 0;
                        T.DFS(i, j, n, m);
                    }
                }
            }
            answer.append(tmp + "\n");
        }
    }

}
