package com.boj_11725;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int n;
    static int[] answer;
    static ArrayList<ArrayList<Integer>> board;

    public void DFS(int L) {
        for (int tmp : board.get(L)) {
            if (answer[tmp] == 0) {
                answer[tmp] = L;
                DFS(tmp);
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        answer = new int[n + 1];
        board = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            board.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            board.get(a).add(b);
            board.get(b).add(a);
        }

        answer[1] = 1;
        T.DFS(1);
        for (int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }
}
