package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 1. Mark source node's distance 0 and other nodes distance as infinity. Create a set of all the unsettled nodes called the
 * unsettled set.
 * 
 * 2. Set the initial node as current.For the current node, consider all of its unsettled neighbors and calculate their tentative
 * distances through the current node. Compare the newly calculated tentative distance to the current assigned value and assign
 * the smaller one.
 * 
 * 3. When we are done considering all of the unsettled neighbors of the current node, mark the current node as settled and remove
 * it from the unsettled set.
 * 
 * 4. Select the unsettled node that is marked with the smallest tentative distance, set it as the new "current node" until either
 * the unsettled set is empty or if the smallest tentative distance among the nodes in the unsettled set is infinity.
 */
public class Dijkstra {
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            unsettledNodes.remove(currentNode);
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node currentNode) {
        Integer currentDistance = currentNode.getDistance();
        if (currentDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(currentDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
            shortestPath.add(currentNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph.print();

        graph = calculateShortestPathFromSource(graph, nodeA);

        graph.printShortestPaths();

    }

}

class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public void print() {
        for (Node node : nodes) {
            for (Entry<Node, Integer> nodeAndDistance : node.adjacentNodes.entrySet()) {
                System.out.printf("%s -- %d --> %s\n", node.getName(), nodeAndDistance.getValue(),
                        nodeAndDistance.getKey().getName());
            }
        }
    }

    public void printShortestPaths() {
        for (Node node : nodes) {
            if (!node.getShortestPath().isEmpty()) {
                for (Node neighbor : node.getShortestPath()) {
                    System.out.printf("%d --> %s -- ", neighbor.getDistance(), neighbor.getName());
                }
                System.out.printf("%d -- %s", node.getDistance(), node.getName());
                System.out.println();
            }
        }
    }
}

class Node {

    private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
