package com.boj_13549;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {
    int time;
    int idx;

    Graph(int time, int idx) {
        this.time = time;
        this.idx = idx;
    }
}

class Main {
    static int[] ch;
    static int e;
    static int answer = Integer.MAX_VALUE;

    public void BFS(int L, int s) {
        Queue<Graph> Q = new LinkedList<>();
        Q.offer(new Graph(L, s));
        boolean flag = false;

        while (!Q.isEmpty()) {
            Graph ob = Q.poll();
            if (flag && answer < ob.time) {
                continue;
            }

            if (ob.idx == e) {
                answer = Math.min(answer, ob.time);
                flag = true;
            }
            ch[ob.idx] = 1;

            int[] arr = {ob.idx + 1, ob.idx - 1, ob.idx * 2};
            for (int j = 0; j < 3; j++) {
                int tmp = arr[j];

                if (0 <= tmp && tmp <= 100000 && ch[tmp] == 0) {
                    if (j == 2) {
                        Q.offer(new Graph(ob.time, tmp));
                    } else {
                        Q.offer(new Graph(ob.time + 1, tmp));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        ch = new int[100001];
        int s = kb.nextInt();
        e = kb.nextInt();

        T.BFS(0, s);
        System.out.println(answer);
    }
}
