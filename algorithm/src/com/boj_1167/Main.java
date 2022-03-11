package com.boj_1167;

import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int vertex;
    int dis;

    Node(int vertex, int dis) {
        this.vertex = vertex;
        this.dis = dis;
    }
}

class Main {
    static int v;
    static ArrayList<ArrayList<Node>> arr;
    static int[] ch;
    static int tmp = Integer.MIN_VALUE;
    static int idx;

    public void DFS(int v, int sum) {
        ch[v] = 1;

        if (tmp < sum) {
            tmp = sum;
            idx = v;
        }

        for (Node n : arr.get(v)) {
            if (ch[n.vertex] == 0) {
                DFS(n.vertex, sum + n.dis);
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        v = kb.nextInt();
        arr = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 1; i <= v; i++) {
            int num = kb.nextInt();
            while (true) {
                int n = kb.nextInt();
                if (n == -1) {
                    break;
                } else {
                    int d = kb.nextInt();
                    arr.get(num).add(new Node(n, d));
                }
            }
        }

        ch = new int[v + 1];
        T.DFS(1, 0);
        ch = new int[v + 1];
        T.DFS(idx, 0);
        System.out.println(tmp);
    }
}
