package com.boj_9205;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int n;
    static ArrayList<Point> pArr;
    static ArrayList<ArrayList<Integer>> arr;

    public boolean BFS(int v) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(v);
        int[] ch = new int[n + 2];
        ch[v] = 1;

        while (!Q.isEmpty()) {
            int ob = Q.poll();
            if (ob == n + 1) {
                return true;
            }

            for (int num : arr.get(ob)) {
                if (ch[num] == 0) {
                    ch[num] = 1;
                    Q.offer(num);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int t = kb.nextInt();
        StringBuilder answer = new StringBuilder();

        while (t-- > 0) {
            n = kb.nextInt();

            pArr = new ArrayList<>();
            for (int i = 0; i < n + 2; i++) {
                int x = kb.nextInt();
                int y = kb.nextInt();
                pArr.add(new Point(x, y));
            }

            arr = new ArrayList<>();
            for (int i = 0; i < n + 2; i++) {
                arr.add(new ArrayList<>());
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = i + 1; j < n + 2; j++) {
                    if (Math.abs(pArr.get(i).x - pArr.get(j).x) + Math.abs(pArr.get(i).y - pArr.get(j).y) <= 1000) {
                        arr.get(i).add(j);
                        arr.get(j).add(i);
                    }
                }
            }

            if (T.BFS(0)) {
                answer.append("happy" + "\n");
            } else {
                answer.append("sad" + "\n");
            }
        }

        System.out.println(answer);
    }
}