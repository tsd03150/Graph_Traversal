package com.boj_11724;

import java.util.Scanner;

// Union & Find
class ReMain {
    static int[] arr;

    public static int Find(int v) {
        if (arr[v] == v) {
            return v;
        }  else {
            return arr[v] = Find(arr[v]);
        }
    }

    public static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);

        arr[fa] = fb;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();

        arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();

            if (Find(a) != Find(b)) {
                Union(a, b);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == i) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
