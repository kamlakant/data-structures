package graph;

import java.util.LinkedList;

/**
 * Data Structures to represent Graph:
 * 
 * Edge List - O(n) Lookup, Adjacency Matrix - O(1) Lookup but Space issue, Adjacency List - O(degree) neighbor find. Array of
 * LinkedLists
 * 
 * BFS - Visit all nearest neighbors first, then the next nearest neighbors and so on. Useful in web crawler, social graphs, connections,
 * recommendations, nearest player etc. Uses a queue.
 * 
 * DFS - Visit the full path for a neighbor first, then full path of another neighbor and so on. Useful in finding shortest paths,
 * path through a maze, cycle detection etc. Uses a stack.
 *
 */
public class SimpleGraph {

    // Number of vertices
    private int V;

    // Adjacency list
    private LinkedList<Integer>[] adj;

    public SimpleGraph(int v) {
        V = v;
        adj = new LinkedList[v];

        // Each element of adjacency list is a LinkedList
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addUniEdge(int from, int to) {
        adj[from].add(to);
    }

    public void addBiEdge(int from, int to) {
        adj[from].add(to);
        adj[to].add(from);
    }

    public void print() {
        for (int i = 0; i < V; i++) {
            System.out.printf("%d -> {", i);
            LinkedList<Integer> neighbors = adj[i];
            for (Integer neighbor : neighbors) {
                System.out.printf("%d, ", neighbor);
            }
            System.out.println("}");
        }
    }

    public static void main(String[] args) {
        SimpleGraph graph = new SimpleGraph(8);
        graph.addBiEdge(0, 1);
        graph.addBiEdge(1, 4);
        graph.addBiEdge(4, 6);
        graph.addBiEdge(6, 0);
        graph.addBiEdge(1, 5);
        graph.addBiEdge(5, 3);
        graph.addBiEdge(3, 0);
        graph.addBiEdge(5, 2);
        graph.addBiEdge(2, 7);

        graph.addUniEdge(7, 6);

        graph.print();
    }

}
