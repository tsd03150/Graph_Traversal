package com.boj_2589;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;

    Graph(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int n;
    static int m;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int BFS(int L, int x, int y) {
        int[][] ch = new int[n][m];
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y));
        ch[x][y] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[nx][ny] == 0 && board[nx][ny] == 'L') {
                        ch[nx][ny] = 1;
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
            L++;
        }
        return L - 1;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'L') {
                    answer = Math.max(answer, T.BFS(0, i, j));
                }
            }
        }
        System.out.println(answer);
    }
}
