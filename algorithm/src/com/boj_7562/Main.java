package com.boj_7562;

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
    static int[][] board;
    static int s;
    static int e;
    static int tmp;
    static boolean flag;
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};

    public void BFS(int L, int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y));

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();
                for (int j = 0; j < 8; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (nx == s && ny == e) {
                        tmp = L+1;
                        return;
                    }
                    if (0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == 0) {
                        board[nx][ny] = 1;
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
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t > 0) {
            n = kb.nextInt();
            board = new int[n][n];
            tmp = 0;
            flag = false;
            int x = kb.nextInt(); // 출발 x좌표
            int y = kb.nextInt(); // 출발 y좌표
            s = kb.nextInt(); // 도착 x좌표
            e = kb.nextInt(); // 도착 y좌표

            if (x == s && y == e) {
                tmp = 0;
            } else {
                board[x][y] = 1;
                T.BFS(0, x, y);
            }
            answer.append(tmp + "\n");
            t--;
        }
        System.out.println(answer);
    }
}
