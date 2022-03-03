package com.boj_13460;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;

    public void DFS(int v, int c) {
        ch[v] = c;

        for (int num : arr.get(v)) {
            if (ch[num] == 0) {
                DFS(num, 3 - c);
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t > 0) {
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

            for (int i = 1; i <= n; i++) {
                if (ch[i] == 0) {
                    T.DFS(i, 1);
                }
            }
            boolean flag = true;
            for (int i = 1; i <= n; i++) {
                for (int j : arr.get(i)) {
                    if (ch[i] == ch[j]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                answer.append("YES" + "\n");
            } else {
                answer.append("NO" + "\n");
            }
            t--;
        }

        System.out.println(answer);
    }
}
