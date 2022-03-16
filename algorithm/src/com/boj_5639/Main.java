package com.boj_5639;

import java.util.Scanner;

class Node {
    int vertex;
    Node lt;
    Node rt;

    Node(int vertex) {
        this.vertex = vertex;
        this.lt = null;
        this.rt = null;
    }
}

class Main {

    public void DFS(Node s, Node e) {
        if (e.vertex < s.vertex) {
            if (s.lt == null) {
                s.lt = e;
            } else {
                DFS(s.lt, e);
            }
        } else {
            if (s.rt == null) {
                s.rt = e;
            } else {
                DFS(s.rt, e);
            }
        }
    }

    public void postOrder(Node node) {
        if (node.lt != null) {
            postOrder(node.lt);
        }
        if (node.rt != null) {
            postOrder(node.rt);
        }
        System.out.println(node.vertex);
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner kb = new Scanner(System.in);
        Node root = new Node(kb.nextInt());

        while (kb.hasNextInt()) {
            Node ob = new Node(kb.nextInt());
            T.DFS(root, ob);
        }
        kb.close();

        T.postOrder(root);
    }
}