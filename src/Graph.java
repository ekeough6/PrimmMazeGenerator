import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;
    private Set<WeightedEdge> edges;

    public Graph(Set<Node> nodes) {
        this(nodes, new HashSet<>());
    }

    public Graph(Set<Node> nodes, Set<WeightedEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Set<WeightedEdge> getEdges() {
        return edges;
    }

    public void addEdge(WeightedEdge edge){
        edges.add(edge);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                '}';
    }
}

class Node {
    private int x, y;

    public Node(int x) {
        this(x, 0);
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class WeightedEdge implements Comparable<WeightedEdge> {
    private Node node1, node2;
    private int weight;
    public WeightedEdge(Node node1, Node node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeightedEdge)) return false;
        WeightedEdge that = (WeightedEdge) o;
        return Objects.equals(node1, that.node1) &&
                Objects.equals(node2, that.node2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(node1, node2);
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.weight;
    }
}
