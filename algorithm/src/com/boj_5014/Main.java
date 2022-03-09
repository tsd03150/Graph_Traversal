package com.boj_5014;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int f;
    static int s;
    static int g;
    static int u;
    static int d;
    static int[] ch;

    public void BFS(int L) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        ch[s] = 1;
        int[] arr = {u, -d};

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                int tmp = Q.poll();

                if (tmp == g) {
                    System.out.println(L);
                    System.exit(0);
                }

                for (int j = 0; j < 2; j++) {
                    int num = tmp + arr[j];
                    if (0 < num && num <= f && ch[num] == 0) {
                        ch[num] = 1;
                        Q.offer(num);
                    }
                }
            }
            L++;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        f = kb.nextInt();
        s = kb.nextInt();
        g = kb.nextInt();
        u = kb.nextInt();
        d = kb.nextInt();
        ch = new int[f + 1];

        T.BFS(0);
        System.out.println("use the stairs");
    }
}
