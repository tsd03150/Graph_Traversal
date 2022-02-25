package com.boj_7576;

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
    static int m;
    static int n;
    static int[][] board;
    static Queue<Graph> Q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer;

    public void BFS(int L) {
        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if ((0 <= nx && nx < n) && (0 <= ny && ny < m) && board[nx][ny] == 0) {
                        board[nx][ny] = 1;
                        Q.offer(new Graph(nx, ny));
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
        m = kb.nextInt(); // 열
        n = kb.nextInt(); // 행
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();

                if (board[i][j] == 1) {
                    Q.offer(new Graph(i, j));
                }
            }
        }
        T.BFS(0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }
}
