package graph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Start at the given node. Mark it visited and put it in queue.
 * 
 * Now, dequeue it and get the list of its neighbors. Mark all of them visited (if not already visited) and put them in the queue.
 * 
 * Repeat the above process until the queue is empty.
 */
public class BFS {

    private int V;
    private LinkedList<Integer>[] adjList;

    public BFS(int v) {
        V = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addBiEdge(int from, int to) {
        adjList[from].add(to);
        adjList[to].add(from);
    }

    public void traverseBfs(int source) {
        // Array to store which nodes have been visited already
        boolean[] visited = new boolean[V];

        // Queue to store which nodes will be visited
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the start node visited and put it in queue
        visited[source] = true;
        queue.add(source);

        // Until the queue is empty, pop the 1st item from queue. Visit it's neighbors and put them in queue.
        while (!queue.isEmpty()) {
            // Pop item at HEAD of queue
            int nextNode = queue.poll();

            System.out.print(nextNode + " ");

            // Get the list of neighbors of the dequeued node
            LinkedList<Integer> neighbors = adjList[nextNode];

            // Mark all neighbors visited IF NOT ALREADY VISITED and put them in queue
            Iterator<Integer> iterator = neighbors.listIterator();
            while (iterator.hasNext()) {
                int neighbor = iterator.next();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

    }

    public void print() {
        for (int i = 0; i < V; i++) {
            System.out.printf("%d -> {", i);
            LinkedList<Integer> neighbors = adjList[i];
            for (Integer neighbor : neighbors) {
                System.out.printf("%d, ", neighbor);
            }
            System.out.println("}");
        }
    }

    public static void main(String[] args) {
        BFS graph = new BFS(8);

        graph.addBiEdge(0, 1);
        graph.addBiEdge(1, 4);
        graph.addBiEdge(4, 6);
        graph.addBiEdge(6, 0);
        graph.addBiEdge(1, 5);
        graph.addBiEdge(5, 3);
        graph.addBiEdge(3, 0);
        graph.addBiEdge(5, 2);
        graph.addBiEdge(2, 7);

        graph.print();

        graph.traverseBfs(0);
    }

}
