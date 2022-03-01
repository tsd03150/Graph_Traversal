package com.boj_10026;

import java.util.Scanner;

class Main {
    static int n;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution() {
        int cnt = 0;
        int[][] ch = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ch[i][j] == 0) {
                    cnt++;
                    ch[i][j] = 1;
                    DFS(i, j, board[i][j], ch);
                }
            }
        }
        return cnt;
    }

    public void DFS(int x, int y, char c, int[][] ch) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (ch[nx][ny] == 0 && board[nx][ny] == c) {
                    ch[nx][ny] = 1;
                    DFS(nx, ny, c, ch);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String tmp = kb.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }

        int answer = T.solution();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'G') {
                    board[i][j] = 'R';
                }
            }
        }

        System.out.println(answer + " " + T.solution());
    }
}
