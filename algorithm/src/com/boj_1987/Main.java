package com.boj_1987;

import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static char[][] board;
    static int[] ch;
    static int answer = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void DFS(int L, int x, int y) {
        answer = Math.max(answer, L);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[board[nx][ny] - 'A'] == 0) {
                ch[board[nx][ny] - 'A'] = 1;
                DFS(L+1, nx, ny);
                ch[board[nx][ny] - 'A'] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new char[n][m];
        ch = new int[26];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        ch[board[0][0] - 'A'] = 1;
        T.DFS(1, 0, 0);
        System.out.println(answer);
    }
}