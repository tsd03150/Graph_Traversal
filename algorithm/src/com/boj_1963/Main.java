package com.boj_1963;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int s;
    static int[] ch;
    static int[] isPrime;
    static int[] cnt;

    public void checkPrime() {
        for (int i = 2; i < 10000; i++) {
            for (int j = i * i; j < 10000; j += i) {
                if (isPrime[j] == 0) {
                    isPrime[j] = 1;
                }
            }
        }
    }

    public void BFS() {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        ch[s] = 1;

        while (!Q.isEmpty()) {
            int num = Q.poll();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) { // 1000 이상
                        continue;
                    }

                    int ob = changePwd(num, i, j);
                    if (ch[ob] == 0 && isPrime[ob] == 0) {
                        ch[ob] = 1;
                        cnt[ob] = cnt[num] + 1;
                        Q.offer(ob);
                    }
                }
            }
        }
    }

    public int changePwd(int num, int i, int j) {
        char[] tmp = String.valueOf(num).toCharArray();
        tmp[i] = (char)(j + '0');
        return Integer.parseInt(String.valueOf(tmp));
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t-- > 0) {
            isPrime = new int[10000]; // 소수 여부
            ch = new int[10000]; // 방문 여부
            cnt = new int[10000]; // 카운텨 여부
            T.checkPrime();

            s = kb.nextInt();
            int e = kb.nextInt();

            T.BFS();
            answer.append(cnt[e] + "\n");
        }
        System.out.println(answer);
    }
}
