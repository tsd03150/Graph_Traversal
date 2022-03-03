package com.boj_16236;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;
    int dis;

    Graph(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

class Main {
    static int n;
    static int[][] board;
    static int[][] ch;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int lev = 2;
    static int ent = 0;
    static int answer = 0;

    static Queue<Graph> Q = new PriorityQueue<>(((o1, o2) -> {
        if (o1.dis == o2.dis) {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            } else {
                return o1.x - o2.x;
            }
        } else {
            return o1.dis - o2.dis;
        }
    }));

    public void BFS() {
        while (!Q.isEmpty()) {
            Graph ob = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (board[nx][ny] <= lev && ch[nx][ny] == 0) {
                        ch[nx][ny] = 1;
                        Q.offer(new Graph(nx, ny, ob.dis + 1));
                    }
                }
            }

            if (Q.peek() != null) {
                Graph tmp = Q.peek();

                if (0 < board[tmp.x][tmp.y] && board[tmp.x][tmp.y] < lev) {
                    ent++;
                    board[tmp.x][tmp.y] = 0;
                    answer += tmp.dis;
                    Q.clear();
                    Q.offer(new Graph(tmp.x, tmp.y, 0));
                    ch = new int[n][n];
                    ch[tmp.x][tmp.y] = 1;
                }

                if (ent == lev) {
                    lev++;
                    ent = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new int[n][n];
        ch = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();

                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    ch[i][j] = 1;
                    Q.offer(new Graph(i, j, 0));
                }
            }
        }
        T.BFS();
        System.out.println(answer);
    }
}