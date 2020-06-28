package com.jason.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 *
 * @author: 祁琦
 * @date: 2020/6/28 16:13
 * version: 1.0
 */
public class Graph {
    /**
     * 顶点的个数
     */
    private int v;
    /**
     * 邻接表
     */
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 无向图一条边存储两次
     *
     * @param s 顶点s
     * @param t 顶点t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 图的广度优先遍历
     * 找出从开始顶点开始到目标顶点之间的最短路径
     *
     * @param s 开始顶点
     * @param t 目标顶点
     */
    public void bfs(int s, int t) {
        if (s == t){return;}
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }
        while (queue.size() > 0) {
            Integer pollNum = queue.poll();
            LinkedList<Integer> edges = adj[pollNum];
            for (Integer edge : edges) {
                if (visited[edge]) {
                    continue;
                }
                prev[edge] = pollNum;
                if (edge == t) {
                    print(prev, s, t);
                    return;
                }
                visited[edge] = true;
                queue.add(edge);
            }
        }
    }

    /**
     * 递归打印s-t的路径
     *
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    public static void main(String[] args) {

        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 5);

        graph.bfs(0, 4);
    }
}
