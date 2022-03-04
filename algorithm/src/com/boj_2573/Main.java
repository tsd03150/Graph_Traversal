package com.boj_2573;

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
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public boolean BFS() {
        int[][] visit = new int[n][m];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] == 0 && board[i][j] != 0) {
                    if (cnt == 1) {
                        return true;
                    }
                    cnt++;
                    Queue<Graph> Q = new LinkedList<>();
                    Q.offer(new Graph(i, j));
                    visit[i][j] = 1;

                    while (!Q.isEmpty()) {
                        Graph ob = Q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = ob.x + dx[k];
                            int ny = ob.y + dy[k];

                            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                                if (visit[nx][ny] == 0 && board[nx][ny] > 0) {
                                    visit[nx][ny] = 1;
                                    Q.offer(new Graph(nx, ny));
                                }
                            }
                        }
                    }
                }
            }
        }

        if (cnt == 0) {
            System.out.println(0);
            System.exit(0);
        }
        return false;
    }



    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        board = new int[n][m];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = kb.nextInt();
            }
        }

        int answer = 0;
        while (true) {
            // 빙하 영역 구하기
            if (T.BFS()) {
                System.out.println(answer);
                return;
            }

            // 바다 탐색
            int[][] ch = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] != 0) {
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (0 <= nx && nx < n && 0 <= ny && ny < m && board[nx][ny] == 0) {
                                cnt++;
                            }
                        }
                        ch[i][j] = cnt;
                    }
                }
            }

            // 빙하 녹이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] - ch[i][j] > 0) {
                        board[i][j] -= ch[i][j];
                    } else {
                        board[i][j] = 0;
                    }
                }
            }

            answer++;
        }
    }
}