package com.boj_1068;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;
    static int cnt;

    public void DFS(int v) {
        if (ch[v] == 1) {
            return;
        }

        ch[v] = 1;
        boolean flag = false;
        for (int num : arr.get(v)) {
            if (ch[num] == 0) {
                flag = true;
                DFS(num);
            }
        }
        if (!flag) {
            cnt++;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        arr = new ArrayList<>();
        ch = new int[n];

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }

        int s = 0;
        for (int i = 0; i < n; i++) {
            int num = kb.nextInt();
            if (num == -1) {
                s = i;
            } else {
                arr.get(num).add(i);
            }
        }

        int m = kb.nextInt();
        ch[m] = 1;
        T.DFS(s);
        System.out.println(cnt);
    }
}
