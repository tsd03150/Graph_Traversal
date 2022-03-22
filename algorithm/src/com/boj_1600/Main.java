package com.boj_1600;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;
    int cnt;
    int r;

    Graph(int x, int y, int cnt, int r) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.r = r;
    }
}

class Main {
    static int k;
    static int m;
    static int n;
    static int[][] board;
    static int[][][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] jx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] jy = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int answer = -1;

    public void BFS() {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(0, 0, 0, k));
        ch[0][0][k] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                if (ob.x == n - 1 && ob.y == m - 1) {
                    answer = ob.cnt;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];
                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (board[nx][ny] == 0 && ch[nx][ny][ob.r] == 0) {
                            ch[nx][ny][ob.r] = 1;
                            Q.offer(new Graph(nx, ny, ob.cnt + 1, ob.r));
                        }
                    }
                }

                if (ob.r > 0) {
                    for (int j = 0; j < 8; j++) {
                        int nx = ob.x + jx[j];
                        int ny = ob.y + jy[j];
                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (board[nx][ny] == 0 && ch[nx][ny][ob.r - 1] == 0) {
                                ch[nx][ny][ob.r - 1] = 1;
                                Q.offer(new Graph(nx, ny, ob.cnt + 1, ob.r - 1));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        k = kb.nextInt();
        m = kb.nextInt();
        n = kb.nextInt();
        board = new int[n][m];
        ch = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        T.BFS();
        System.out.println(answer);
    }
}