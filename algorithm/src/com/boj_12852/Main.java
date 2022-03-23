package com.boj_12852;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Number {
    int num;
    Number pre;

    Number(int num, Number pre) {
        this.num = num;
        this.pre = pre;
    }
}

class Main {
    static int n;
    static int[] ch;
    static int answer;
    static Number number;

    public void BFS(int L) {
        Queue<Number> Q = new LinkedList<>();
        Q.offer(new Number(n, null));
        ch[n] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Number ob = Q.poll();
                if (ob.num == 1) {
                    answer = L;
                    number = ob;
                    return;
                }

                int[] arr = {3, 2};
                for (int j = 0; j < 2; j++) {
                    if (ob.num % arr[j] == 0) {
                        int tmp = ob.num / arr[j];
                        if (ch[tmp] == 0) {
                            ch[tmp] = 1;
                            Q.offer(new Number(tmp, ob));
                        }
                    }
                }

                if (ch[ob.num - 1] == 0) {
                    ch[ob.num - 1] = 1;
                    Q.offer(new Number(ob.num - 1, ob));
                }
            }
            L++;
        }
    }

    public void DFS(Number v) {
        if (v.pre != null) {
            DFS(v.pre);
        }
        System.out.print(v.num + " ");
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        ch = new int[n + 1];
        T.BFS(0);

        System.out.println(answer);
        T.DFS(number);
    }
}
