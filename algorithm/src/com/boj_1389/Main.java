package com.boj_1389;

import java.util.Scanner;

class Main {
    public static final int NUM = 5000;

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] board = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = NUM;
                }
            }
        }

        int m = kb.nextInt();
        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            board[a][b] = 1;
            board[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (board[i][j] > board[i][k] + board[k][j]) {
                        board[i][j] = board[i][k] + board[k][j];
                    }
                }
            }
        }

        int answer = 0;
        int ob = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int tmp = 0;
            for (int j = 1; j <= n; j++) {
                tmp += board[i][j];
            }

            if (ob > tmp) {
                ob = tmp;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}