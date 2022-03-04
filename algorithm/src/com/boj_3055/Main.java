package com.boj_3055;

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
    static Queue<Graph> Q = new LinkedList<>();
    static Queue<Graph> wQ = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] ch;
    static int[][] wch;
    static int answer;

    public void BFS(int L) {
        while (!Q.isEmpty()) {
            wBFS();
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m && ch[nx][ny] == 0 && wch[nx][ny] == 0) {
                        if (board[nx][ny] == 'D') {
                            answer = L + 1;
                            return;
                        }

                        if (board[nx][ny] == '.') {
                            Q.offer(new Graph(nx, ny));
                            ch[nx][ny] = 1;
                        }
                    }
                }
            }
            L++;
        }
    }

    public void wBFS() {
        int len = wQ.size();

        for (int i = 0; i < len; i++) {
            Graph ob = wQ.poll();

            for (int j = 0; j < 4; j++) {
                int nx = ob.x + dx[j];
                int ny = ob.y + dy[j];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && wch[nx][ny] == 0 && board[nx][ny] == '.') {
                    wch[nx][ny] = 1;
                    wQ.offer(new Graph(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new char[n][m];
        ch = new int[n][m];
        wch = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);

                if (board[i][j] == 'S') {
                    Q.offer(new Graph(i, j));
                    ch[i][j] = 1;
                }

                if (board[i][j] == '*') {
                    wQ.offer(new Graph(i, j));
                    wch[i][j] = 1;
                }
            }
        }

        T.BFS(0);
        if (answer > 0) {
            System.out.println(answer);
        } else {
            System.out.println("KAKTUS");
        }
    }
}