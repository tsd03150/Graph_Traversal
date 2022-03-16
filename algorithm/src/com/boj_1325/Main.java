package com.boj_1325;

import java.util.*;

class Main {
    static ArrayList<ArrayList<Integer>> arr;
    static int n;
    static int[] answer;

    public int BFS(int L, int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        int[] ch = new int[n + 1];
        ch[v] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                if (Q.size() > 0) {
                    int ob = Q.poll();
                    for (int num : arr.get(ob)) {
                        if (ch[num] == 0) {
                            L++;
                            ch[num] = 1;
                            Q.offer(num);
                        }
                    }
                }
            }
        }
        return L;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int m = kb.nextInt();
        arr = new ArrayList<>();
        answer = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            arr.get(b).add(a);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer[i] = T.BFS(0, i);
            max = Math.max(max, answer[i]);
        }

        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (answer[i] == max) {
                tmp.add(i);
            }
        }

        Collections.sort(tmp);
        for (int num : tmp) {
            System.out.print(num + " ");
        }
    }
}