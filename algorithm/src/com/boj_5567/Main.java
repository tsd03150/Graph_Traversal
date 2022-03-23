package com.boj_5567;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;
    static int answer;

    public void BFS(int L, int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        ch[v] = 1;
        int cnt = 0;

        while (!Q.isEmpty()) {
            if (L > 1) {
                answer = cnt;
                return;
            }

            int len = Q.size();
            for (int i = 0; i < len; i++) {
                if (!Q.isEmpty()) {
                    int num = Q.poll();

                    for (int ob : arr.get(num)) {
                        if (ch[ob] == 0) {
                            ch[ob] = 1;
                            cnt++;
                            Q.offer(ob);
                        }
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
        int m = kb.nextInt();
        arr = new ArrayList<>();
        ch = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        T.BFS(0, 1);
        System.out.println(answer);
    }
}
