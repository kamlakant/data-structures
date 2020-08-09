package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Start at the given node. Mark it visited and put it in Stack.
 * 
 * Now, pop it and get the list of its neighbors. Mark all of them visited (if not already visited) and put them in the stack.
 * 
 * Repeat the above process until the stack is empty.
 */
public class DFS {

    private int V;
    private LinkedList<Integer>[] adjList;

    public DFS(int v) {
        V = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void traverseDfs(int source) {
        // Array to store which nodes have been visited already
        boolean[] visited = new boolean[V];

        // Stack to store nodes which will be visited
        Stack<Integer> stack = new Stack<>();

        // Mark the source node visited and put it in stack
        visited[source] = true;
        stack.push(source);

        // Until the stack is empty, get the top node and visit its neighbors if not already visited. Add them to stack
        while (!stack.isEmpty()) {
            int nextNode = stack.pop();
            System.out.print(nextNode + " ");

            // Get the neighbors
            LinkedList<Integer> neighbors = adjList[nextNode];

            Iterator<Integer> iterator = neighbors.listIterator();

            // Mark all neighbors visited IF NOT ALREADY VISITED and put them in stack
            while (iterator.hasNext()) {
                int neighbor = iterator.next();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
    }

    public void addBiEdge(int from, int to) {
        adjList[from].add(to);
        adjList[to].add(from);
    }

    public static void main(String[] args) {
        DFS graph = new DFS(8);

        graph.addBiEdge(0, 1);
        graph.addBiEdge(1, 4);
        graph.addBiEdge(4, 6);
        graph.addBiEdge(6, 0);
        graph.addBiEdge(1, 5);
        graph.addBiEdge(5, 3);
        graph.addBiEdge(3, 0);
        graph.addBiEdge(5, 2);
        graph.addBiEdge(2, 7);

        graph.traverseDfs(0);

    }

}
