import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Primm {
    public static void main(String[] args) {
        Set<Node> nodes = new HashSet<>();
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                nodes.add(new Node(i, j));
            }
        }

        Graph g = new Graph(nodes);

        for (Node node:nodes) {
            for (Node node1:nodes) {
                if(!node.equals(node1) && (Math.abs(node.getX() - node1.getX()) == 1 && Math.abs(node.getY() - node1.getY()) == 0 || Math.abs(node.getX() - node1.getX()) == 0 && Math.abs(node.getY() - node1.getY()) == 1)) {
                    WeightedEdge edge = new WeightedEdge(node, node1, (int)(Math.random() * 1000));
                    g.addEdge(edge);
                }

            }
        }

        System.out.println(makeMaze(g, 40));
        System.out.println(makeMaze(primmsAlgorithm(g), 40));
    }

    public static Graph primmsAlgorithm(Graph g) {
        Set<Node> oldNodes = g.getNodes();
        Set<WeightedEdge> oldEdges = g.getEdges();
        Set<Node> newNodes = new HashSet<>();
        Set<WeightedEdge> newEdges = new HashSet<>();
        newNodes.add(oldNodes.iterator().next());
        while(newNodes.size() < oldNodes.size()) {
            Set<WeightedEdge> availableEdges = getAvailableEdges(newNodes, oldEdges);
            Iterator<WeightedEdge> iterator = availableEdges.iterator();
            WeightedEdge min = iterator.next();
            while(iterator.hasNext()) {
                WeightedEdge edge = iterator.next();
                if(min.getWeight() > edge.getWeight()) {
                    min = edge;
                }
            }
            newNodes.add(min.getNode2());
            newEdges.add(min);
        }

        return new Graph(newNodes, newEdges);
    }

    public static Set<WeightedEdge> getAvailableEdges(Set<Node> nodes, Set<WeightedEdge> edges) {
        Set<WeightedEdge> availableEdges = new HashSet<>();
        Iterator<WeightedEdge> iterator = edges.iterator();
        while(iterator.hasNext()) {
            WeightedEdge e = iterator.next();
            if(nodes.contains(e.getNode1()) && !nodes.contains(e.getNode2()))
                availableEdges.add(e);
        }
        return availableEdges;
    }

    public static String makeMaze(Graph g, int size) {
        char[][] maze = new char[size*2+1][size*2+1];
        for (int i = 0; i < maze.length ; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[i][j] = (i%2==1 && j%2==1) ? ' ' : '#';
            }
        }

        Iterator<WeightedEdge> iterator = g.getEdges().iterator();
        while(iterator.hasNext()) {
            int x1, x2, y1, y2;
            WeightedEdge edge = iterator.next();
            x1 = edge.getNode1().getX() * 2 + 1;
            y1 = edge.getNode1().getY() * 2 + 1;
            x2 = edge.getNode2().getX() * 2 + 1;
            y2 = edge.getNode2().getY() * 2 + 1;
            maze[(x1+x2)/2][(y1+y2)/2] = ' ';
        }

        String mazeString = "";
        for (int i = 0; i < maze.length ; i++) {
            for (int j = 0; j < maze.length; j++) {
                mazeString+=maze[i][j];
            }
            mazeString+="\n";
        }
        return mazeString;
    }

}
