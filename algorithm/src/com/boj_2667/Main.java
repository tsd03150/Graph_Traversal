package com.boj_2667;

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
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int BFS(int L, int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(x, y));

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                Graph ob = Q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = ob.x + dx[j];
                    int ny = ob.y + dy[j];

                    if ((0 <= nx && nx < n) && (0 <= ny && ny < n) && board[nx][ny] == '1') {
                        L++;
                        board[nx][ny] = '0';
                        Q.offer(new Graph(nx, ny));
                    }
                }
            }
        }

        return L;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String tmp = kb.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '1') {
                    cnt++;
                    board[i][j] = '0';
                    answer.add(T.BFS(1, i, j));
                }
            }
        }

        System.out.println(cnt++);
        Collections.sort(answer);
        for (int num : answer) {
            System.out.println(num);
        }

    }
}
