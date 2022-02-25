package com.boj_1012;

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
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public void BFS(int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y));

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if ((0 <= nx && nx < n) && (0 <= ny && ny < m) && board[nx][ny] == 1) {
                        board[nx][ny] = 0;
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t > 0) {
            m = kb.nextInt(); // 열
            n = kb.nextInt(); // 행
            int e = kb.nextInt(); // 간선
            board = new int[n][m];

            for (int i = 0; i < e; i++) {
                int a = kb.nextInt();
                int b = kb.nextInt();

                board[b][a] = 1;
            }

            int tmp = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        tmp++;
                        board[i][j] = 0;
                        T.BFS(i, j);
                    }
                }
            }

            answer.append(tmp + "\n");
            t--;
        }

        System.out.println(answer);
    }
}
