package com.boj_11559;

import java.util.ArrayList;
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
    static int[][] ch;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, - 1, 1};

    public boolean BFS(int L, int x, int y, char c) {
        Queue<Graph> Q = new LinkedList<>();
        ArrayList<Graph> arr = new ArrayList<>();
        Q.offer(new Graph(x, y));
        arr.add(new Graph(x, y));
        ch[x][y] = 1;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (board[nx][ny] == c && ch[nx][ny] == 0) {
                        L++;
                        ch[nx][ny] = 1;
                        Q.offer(new Graph(nx, ny));
                        arr.add(new Graph(nx, ny));
                    }
                }
            }
        }

        if (L >= 4) {
            for (Graph ob : arr) {
                board[ob.x][ob.y] = '.';
            }
            return true;
        }

        return false;
    }

    public void relocation() {
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] == '.') {
                    cnt++;
                } else {
                    if (cnt > 0) {
                        board[j + cnt][i] = board[j][i];
                        board[j][i] = '.';
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = 12;
        m = 6;
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = kb.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int answer = 0;
        while (true) {
            boolean isPop = false;
            ch = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] != '.' && ch[i][j] == 0) {
                        if (T.BFS(1, i, j, board[i][j])) {
                            isPop = true;
                        }
                    }
                }
            }
            T.relocation();

            if (isPop) {
                answer++;
            } else {
                System.out.println(answer);
                return;
            }
        }
    }
}
