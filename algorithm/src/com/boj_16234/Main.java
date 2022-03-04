package com.boj_16234;

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
    static int s;
    static int e;
    static int[][] board;
    static int[][] ch;
    static ArrayList<Graph> arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int BFS(int x, int y) {
        Queue<Graph> Q = new LinkedList<>();
        arr = new ArrayList<>();
        Q.offer(new Graph(x, y));
        arr.add(new Graph(x, y));

        int sum = board[x][y];
        ch[x][y] = 1;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = ob.x + dx[i];
                int ny = ob.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && ch[nx][ny] == 0) {
                    int num = Math.abs(board[ob.x][ob.y] - board[nx][ny]);
                    if (s <= num && num <= e) {
                        ch[nx][ny] = 1;
                        sum += board[nx][ny];
                        Q.offer(new Graph(nx, ny));
                        arr.add(new Graph(nx, ny));
                    }
                }
            }
        }
        return sum;
    }

    public void change(int sum) {
        int num = sum / arr.size();

        for (Graph tmp : arr) {
            board[tmp.x][tmp.y] = num;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        s = kb.nextInt();
        e = kb.nextInt();
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        int answer = 0;
        while (true) {
            ch = new int[n][n];
            boolean flag = false;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ch[i][j] == 0) {
                        sum = T.BFS(i, j);
                    }
                    if (arr.size() > 1) {
                        flag = true;
                        T.change(sum);
                    }
                }
            }

            if (flag) {
                answer++;
            } else {
                System.out.println(answer);
                return;
            }
        }

    }
}
