package com.boj_2206;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int w; // 벽 유무
    int x;
    int y;

    Graph(int w, int x, int y) {
        this.w = w;
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void BFS(int L, int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(0, x, y));

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                if (ob.x == n - 1 && ob.y == m - 1) {
                    System.out.println(L);
                    System.exit(0);
                }

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {

                        if (board[nx][ny] == 0) { // 벽이 아닌 경우
                            if (ob.w == 0 && ch[0][nx][ny] == 0) { // 부신 벽이 없는 경우
                                ch[0][nx][ny] = 1;
                                Q.offer(new Graph(0, nx, ny));
                            } else if (ob.w == 1 && ch[1][nx][ny] == 0) { // 부신 벽이 있는 경우
                                ch[1][nx][ny] = 1;
                                Q.offer(new Graph(1, nx, ny));
                            }
                        } else { // 벽인 경우
                            if (ob.w == 0) { // 벽을 한번도 부수지 않은 상태
                                ch[1][nx][ny] = 1;
                                Q.offer(new Graph(1, nx, ny));
                            }
                        }
                    }
                }
            }
            L++;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];
        ch = new int[2][n][m];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        ch[0][0][0] = 1;
        T.BFS(1, 0, 0);
        System.out.println(-1);
    }
}