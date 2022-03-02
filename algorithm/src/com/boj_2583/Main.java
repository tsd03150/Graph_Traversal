package com.boj_2583;

import java.util.*;

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
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int BFS(int L, int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y));
        board[x][y] = 1;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    Q.offer(new Graph(nx, ny));
                    L++;
                }
            }
        }
        return L;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];
        int k = kb.nextInt();

        for (int t = 0; t < k; t++) {
            int x1 = kb.nextInt();
            int y1 = kb.nextInt();
            int x2 = kb.nextInt();
            int y2 = kb.nextInt();

            for (int i = y1; i < y2; i++) {
                for (int j = x1; j < x2; j++) {
                    board[i][j] = 1;
                }
            }
        }

        int cnt = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    cnt++;
                    int tmp = T.BFS(1, i, j);
                    answer.add(tmp);
                }
            }
        }
        Collections.sort(answer);

        System.out.println(cnt);
        for (int num : answer) {
            System.out.print(num + " ");
        }
    }
}
