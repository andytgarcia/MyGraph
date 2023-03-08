import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MyGraph {
    private int numVertices;
    private HashMap<Vertex, ArrayList<GraphPairing>> graph;
    private int numEdges;

    public MyGraph() {
        numEdges = 0;
        numVertices = 0;
        graph = new HashMap<>();
    }


    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }


    public void insertVertex(String name) {
        Vertex temp = new Vertex(name);
        //assume the given name does not exist in the map
        graph.put(temp, null);
        numVertices++;
    }

    public void print(){
        Set<Vertex> vertexList = graph.keySet();

        for(Vertex v: vertexList) {
            System.out.print(v + ": ");
            System.out.println(graph.get(v));
        }
    }


    public void insertEdge(String v1, String v2, String edgename) {
        Set<Vertex> vertices = graph.keySet();
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        Edge edge = new Edge(edgename);

        for(Vertex v: vertices){
            if (v.compareTo(new Vertex(v1)) == 0) {
                vertex1 = v;
            }
            if (v.compareTo(new Vertex(v2)) == 0) {
                vertex2 = v;
            }
        }


        //System.out.println("contains v1: " + graph.containsKey(vertex1));
        //System.out.println("contains v2: " + graph.containsKey(vertex2));
        ArrayList<GraphPairing> v1EdgeList = graph.get(vertex1);
        if (v1EdgeList == null) {
            graph.put(vertex1, new ArrayList<>());
            graph.get(vertex1).add(new GraphPairing(vertex1, edge));
        }

        ArrayList<GraphPairing> v2EdgeList = graph.get(vertex2);
        if (v2EdgeList == null) {
            graph.put(vertex2, new ArrayList<>());
            graph.get(vertex2).add(new GraphPairing(vertex2, edge));
        }

        numEdges++;

    }

    public Set<Vertex> vertices() {return graph.keySet();}

    public Set<Edge> edges() {
        return null;
    }

    public Edge getEdge(Vertex v, Vertex u) {
       return null;
    }
}
