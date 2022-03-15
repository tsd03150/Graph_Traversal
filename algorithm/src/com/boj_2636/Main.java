package com.boj_2636;

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
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void BFS(int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        int[][] ch = new int[n][m];
        Q.offer(new Graph(x, y));
        ch[x][y] = 1;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    if (board[nx][ny] == 1) {
                        board[nx][ny] = 0;
                    } else {
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
        }
    }

    public int count() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        int answer = 0;
        int cnt = 0;
        while (true) {
            int tmp = T.count();
            if (tmp == 0) {
                System.out.println(answer);
                System.out.println(cnt);
                System.exit(0);
            } else {
                cnt = tmp;
            }
            T.BFS(0, 0);
            answer++;
        }
    }
}
