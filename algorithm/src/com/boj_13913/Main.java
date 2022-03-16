package com.boj_13913;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int num;
    Point pre;

    Point(int num, Point pre) {
        this.num = num;
        this.pre = pre;
    }
}

class Main {
    static int s;
    static int e;
    static int[] ch;
    static int answer;
    static Point tmp;

    public void BFS(int L) {
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(s, null));
        ch[s] = 1;

        while (!Q.isEmpty()) {
            int len = Q.size();
            for (int i = 0; i < len; i++) {
                Point ob = Q.poll();
                if (ob.num == e) {
                    answer = L;
                    tmp = ob;
                    return;
                }

                int[] dis = {ob.num + 1, ob.num - 1, ob.num * 2};
                for (int j = 0; j < 3; j++) {
                    int tmp = dis[j];
                    if (0 <= tmp && tmp <= 100000 && ch[tmp] == 0) {
                        ch[tmp] = 1;
                        Q.offer(new Point(tmp, ob));
                    }
                }
            }
            L++;
        }
    }

    public void DFS(Point p) {
        if (p.pre != null) {
            DFS(p.pre);
        }
        System.out.print(p.num + " ");
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        s = kb.nextInt();
        e = kb.nextInt();
        ch = new int[100001];

        T.BFS(0);
        System.out.println(answer);
        T.DFS(tmp);
    }
}
