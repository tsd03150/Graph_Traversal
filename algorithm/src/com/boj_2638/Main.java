package com.boj_2638;

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
    static int[][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Graph> Q = new LinkedList<>();

    public void cBFS() {
        Queue<Graph> cQ = new LinkedList<>();
        cQ.offer(new Graph(0, 0));
        ch = new int[n][m];
        ch[0][0] = 1;

        while (!cQ.isEmpty()) {
            Graph ob = cQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[nx][ny] == 0) {
                    ch[nx][ny] = 1;
                    if (board[nx][ny] == 0) {
                        cQ.offer(new Graph(nx, ny));
                    } else if (board[nx][ny] == 1) {
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
        }
    }

    public void BFS() {
        int[][] copy = new int[n][m];

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (board[nx][ny] == 0 && ch[nx][ny] == 1) {
                        cnt++;
                        if (cnt >= 2) {
                            copy[ob.x][ob.y] = 1;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 1) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public boolean count() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
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
        while (true) {
            if (T.count()) {
                System.out.println(answer);
                return;
            }

            T.cBFS();
            T.BFS();
            answer++;
        }
    }
}
