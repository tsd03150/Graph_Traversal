package com.boj_9466;

import java.util.Scanner;

class Main {
    static int n;
    static int[] link;
    static int[] ch;
    static int[] done;
    static int cnt;

    public void DFS(int pos) {
        ch[pos] = 1;
        int next = link[pos];

        if (ch[next] == 0) {
            DFS(next);
        } else {
            if (done[next] == 0) {
                cnt++;

                while (pos != next) {
                    cnt++;
                    next = link[next];
                }
            }
        }
        done[pos] = 1;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t > 0) {
            n = kb.nextInt();
            ch = new int[n + 1];
            done = new int[n + 1];
            cnt = 0;
            link = new int[n + 1];
            for (int i = 0; i < n; i++) {
                link[i + 1] = kb.nextInt();
            }

            for (int i = 1; i <= n; i++) {
                T.DFS(i);
            }

            answer.append(n - cnt + "\n");
            t--;
        }

        System.out.println(answer);
    }
}