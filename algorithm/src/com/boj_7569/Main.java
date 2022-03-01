package com.boj_7569;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int z;
    int x;
    int y;

    Graph(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int n;
    static int m;
    static int h;
    static int[][][] board;
    static Queue<Graph> Q = new LinkedList<>();
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};
    static int answer;

    public void BFS(int L) {
        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                for (int j = 0; j < 6; j++) {
                    int nz = ob.z + dz[j];
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nz && nz < h && 0 <= nx && nx < n && 0 <= ny && ny < m && board[nz][nx][ny] == 0) {
                        board[nz][nx][ny] = 1;
                        Q.offer(new Graph(nz, nx, ny));
                    }
                }
            }
            L++;
        }
        answer = L - 1;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        m = kb.nextInt();
        n = kb.nextInt();
        h = kb.nextInt();
        board = new int[h][n][m];

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[k][i][j] = kb.nextInt();

                    if (board[k][i][j] == 1) {
                        Q.offer(new Graph(k, i, j));
                    }
                }
            }
        }

        T.BFS(0);

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[k][i][j] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}