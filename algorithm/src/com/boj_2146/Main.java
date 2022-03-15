package com.boj_2146;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int x;
    int y;
    int cnt;

    Graph(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Main {
    static int n;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int num = 2;
    static int[][] visit;
    static int answer;

    public int BFS(int x, int y, int name) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y, 0));
        int[][] ch = new int[n][n];
        ch[x][y] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();
                if (ob.cnt > answer) {
                    continue;
                }

                if (board[ob.x][ob.y] > 0 && board[ob.x][ob.y] != name) {
                    return ob.cnt - 1;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n && ch[nx][ny] == 0) {
                        Q.offer(new Graph(nx, ny, ob.cnt + 1));
                        ch[nx][ny] = 1;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void naming(int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y, 0));
        board[x][y] = num;
        visit[x][y] = 1;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (board[nx][ny] == 1 && visit[nx][ny] == 0) {
                        visit[nx][ny] = 1;
                        board[nx][ny] = num;
                        Q.offer(new Graph(nx, ny, 0));
                    }
                }
            }
        }
        num++;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && visit[i][j] == 0) {
                    T.naming(i, j);
                }
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    answer = Math.min(answer, T.BFS(i, j, board[i][j]));
                }
            }
        }

        System.out.println(answer);
    }
}
