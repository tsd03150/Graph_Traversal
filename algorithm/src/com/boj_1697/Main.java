package com.boj_1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int k;
    static int[] board;
    static int answer;

    public void BFS(int L, int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        board[v] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                int num = Q.poll();
                int[] tmp = {num + 1, num - 1, num * 2};

                for (int j = 0; j < 3; j++) {
                    int ob = tmp[j];

                    if (ob == k) {
                        answer = L + 1;
                        return;
                    }

                    if (0 <= ob && ob < 100001 && board[ob] == 0) {
                        board[ob] = 1;
                        Q.offer(ob);
                    }
                }
            }
            L++;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        k = kb.nextInt();
        board = new int[100001];

        if (n == k) {
            answer = 0;
        } else {
            T.BFS(0, n);
        }

        System.out.println(answer);
    }
}