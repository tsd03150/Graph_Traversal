package com.boj_2644;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int n;
    static int s;
    static int e;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;

    public void DFS(int L, int s) {
        if (s == e) {
            System.out.println(L);
            System.exit(0);
        } else {
            for (int num : arr.get(s)) {
                if (ch[num] == 0) {
                    ch[num] = 1;
                    DFS(L+1, num);
                }

            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        s = kb.nextInt();
        e = kb.nextInt();
        arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }
        ch = new int[n + 1];

        int m = kb.nextInt();
        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        T.DFS(0, s);
        System.out.println(-1);
    }
}
