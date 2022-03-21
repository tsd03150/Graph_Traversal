package com.boj_12851;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int n;
    static int m;
    static int[] ch;
    static int answer;
    static int cnt;

    public void BFS(int L) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(n);
        boolean flag = false;

        while (!Q.isEmpty()) {
            if (flag) {
                return;
            }

            int len = Q.size();
            for (int i = 0; i < len; i++) {
                if (Q.size() > 0) {
                    int num = Q.poll();
                    ch[num] = 1;
                    if (num == m) {
                        answer = L;
                        flag = true;
                        cnt++;
                    }

                    int[] d = {num + 1, num - 1, num * 2};
                    for (int j = 0; j < 3; j++) {
                        int tmp = d[j];

                        if (0 <= tmp && tmp <= 100000 && ch[tmp] == 0) {
                            Q.offer(tmp);
                        }
                    }
                }
            }
            L++;
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        ch = new int[100001];
        ch[n] = 1;
        T.BFS(0);

        System.out.println(answer);
        System.out.println(cnt);
    }
}
