package com.boj_17142;

import java.util.*;

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
    static int m;
    static int[][] board;
    static Graph[] ch;
    static ArrayList<Graph> arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public void DFS(int L, int v) {
        if (L == m) {
            BFS();
        } else {
            for (int i = v; i < arr.size(); i++) {
                ch[L] = arr.get(i);
                DFS(L + 1, i + 1);
            }
        }
    }

    public void BFS() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = board[i][j];
            }
        }

        Queue<Graph> Q = new LinkedList<>();
        int[][] visit = new int[n][n];
        int[][] po = new int[n][n];
        for (int i = 0; i < m; i++) {
            Q.offer(ch[i]);
            visit[ch[i].x][ch[i].y] = 1;
        }

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && visit[nx][ny] == 0) {
                    visit[nx][ny] = 1;
                    if (copy[nx][ny] == 0) {
                        Q.offer(new Graph(nx, ny, ob.cnt + 1));
                        po[nx][ny] = ob.cnt + 1;
                    } else if (copy[nx][ny] == 2) {
                        Q.offer(new Graph(nx, ny, ob.cnt + 1));
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 0 && visit[i][j] == 0) {
                    return;
                }
                max = Math.max(max, po[i][j]);
            }
        }
        answer = Math.min(answer, max);
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][n];

        arr = new ArrayList<>();
        int znt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
                if (board[i][j] == 2) {
                    arr.add(new Graph(i, j, 0));
                }
                if (board[i][j] == 0) {
                    znt++;
                }
            }
        }
        if (znt == 0) {
            answer = 0;
        } else {
            ch = new Graph[m];
            T.DFS(0, 0);
            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }
        }

        System.out.println(answer);
    }
}
