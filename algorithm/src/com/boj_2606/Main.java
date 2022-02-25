package com.boj_2606;

import java.util.*;

class Main {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] ch;
    static int answer;

    public void BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        ch[v] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();

            for (int i = 0; i < len; i++) {
                int num = Q.poll();

                for (int ob : arr.get(num)) {
                    if (ch[ob] == 0) {
                        ch[ob] = 1;
                        answer++;
                        Q.offer(ob);
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

        T.BFS(1); // 1에서 시작
        System.out.println(answer);
    }

}
