package com.boj_1260;

import java.util.*;

class Main {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static int[] ch;

    public void DFS(int v) {
        ch[v] = 1;
        System.out.print(v + " ");

        for (int num : arr.get(v)) {
            if (ch[num] == 0) {
                DFS(num);
            }
        }
    }

    public void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        ch[v] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                int num = Q.poll();
                System.out.print(num + " ");

                for (int tmp : arr.get(num)) {
                    if (ch[tmp] == 0) {
                        ch[tmp] = 1;
                        Q.offer(tmp);
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
        int v = kb.nextInt();
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

        for (int i = 0; i <= n; i++) {
            Collections.sort(arr.get(i));
        }

        T.DFS(v);
        System.out.println();
        Arrays.fill(ch, 0);
        T.BFS(v);
    }

}
