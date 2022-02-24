package com.boj_2178;

import java.util.Scanner;

class Main_DFS {
    static int n;
    static int m;
    static int[][] ch;
    static char[][] board;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void DFS(int x, int y, int sum) {
        if (sum > answer) {
            return;
        }

        if (x == n && y == m) {
            answer = Math.min(answer, ch[x][y]);
        } else {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if ((0 < nx && nx <= n) && (0 < ny && ny <= m) && board[nx][ny] == '1') {
                    ch[nx][ny] = ch[x][y] + 1;
                    board[nx][ny] = '0';
                    DFS(nx, ny, ch[nx][ny]);
                    board[nx][ny] = '1';
                }
            }
        }
    }

    public static void main(String[] args) {
        Main_DFS T = new Main_DFS();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new char[n + 1][m + 1];
        ch = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String str = kb.next();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1);
            }
        }

        ch[1][1] = 1;
        T.DFS(1, 1, 0);
        System.out.println(answer);
    }

}
