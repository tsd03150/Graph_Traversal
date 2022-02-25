package com.boj_14502;

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
    static int w = 3; // 벽의 수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;

    public void DFS(int L) {
        if (L == w) {
            BFS();
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        DFS(L+1);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public void BFS() {
        Queue<Graph> Q = new LinkedList<>();
        int[][] copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) {
                    Q.offer(new Graph(i, j));
                }
            }
        }

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m && copy[nx][ny] == 0) {
                        copy[nx][ny] = 2;
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
        }
        safe(copy);
    }

    public void safe(int[][] copy) {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) {
                    tmp++;
                }
            }
        }
        answer = Math.max(answer, tmp);
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

        T.DFS(0);
        System.out.println(answer);

    }
}
