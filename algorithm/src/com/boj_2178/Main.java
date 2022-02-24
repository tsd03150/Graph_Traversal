package com.boj_2178;

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
    static int answer;

    public void BFS(int L) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(0, 0));
        board[0][0] = 0;

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (nx == n-1 && ny == m-1) {
                        answer = L+1;
                        return;
                    }

                    if ((0 <= nx && nx < n) && (0 <= ny && ny < m) && board[nx][ny] == '1') {
                        board[nx][ny] = '0';
                        Q.offer(new Graph(nx, ny));
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
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        T.BFS(1);
        System.out.println(answer);
    }
}
