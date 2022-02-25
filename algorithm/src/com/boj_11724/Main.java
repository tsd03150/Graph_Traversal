package com.boj_11724;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;

    public void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                int ob = Q.poll();
                for (int num : arr.get(ob)) {
                    if (ch[num] == 0) {
                        ch[num] = 1;
                        Q.offer(num);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
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

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (ch[i] == 0) {
                ch[i] = 1;
                answer++;
                T.BFS(i);
            }
        }

        System.out.println(answer);
    }
}
