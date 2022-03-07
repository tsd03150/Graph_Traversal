package com.boj_1967;

import java.util.ArrayList;
import java.util.Scanner;

class Graph {
    int vertex;
    int point;

    Graph(int vertex, int point) {
        this.vertex = vertex;
        this.point = point;
    }
}

class Main {
    static int idx;
    static int tmp = Integer.MIN_VALUE;
    static ArrayList<ArrayList<Graph>> arr;
    static int[] ch;

    public void DFS(int vertex, int sum) {
        if (tmp < sum) {
            tmp = sum;
            idx = vertex;
        }

        for (Graph ob : arr.get(vertex)) {
            if (ch[ob.vertex] == 0) {
                ch[ob.vertex] = 1;
                DFS(ob.vertex, ob.point + sum);
            }
        }
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        arr = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            arr.get(a).add(new Graph(b, c));
            arr.get(b).add(new Graph(a, c));
        }

        ch = new int[n + 1];
        ch[1] = 1;
        T.DFS(1, 0);

        ch = new int[n + 1];
        ch[idx] = 1;
        T.DFS(idx, 0);
        System.out.println(tmp);
    }
}