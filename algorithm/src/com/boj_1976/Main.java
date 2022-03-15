package com.boj_1976;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> arr;

    public boolean BFS(int s ,int e) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        int[] ch = new int[n + 1];
        ch[s] = 1;

        while (!Q.isEmpty()) {
            int ob = Q.poll();
            if (ob == e) {
                return true;
            }

            for (int tmp : arr.get(ob)) {
                if (ch[tmp] == 0) {
                    ch[tmp] = 1;
                    Q.offer(tmp);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }
        int m = kb.nextInt();

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
                if (board[i][j] == 1) {
                    arr.get(i + 1).add(j + 1);
                    arr.get(j + 1).add(i + 1);
                }
            }
        }

        int[] target = new int[m];
        for (int i = 0; i < m; i++) {
            target[i] = kb.nextInt();
        }

        for (int i = 0; i < m - 1; i++) {
            if(!T.BFS(target[i], target[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
