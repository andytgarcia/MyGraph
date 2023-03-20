import java.beans.VetoableChangeListener;
import java.util.*;

public class MyGraph {
    private int numVertices;
    private HashMap<Vertex, ArrayList<GraphPairing>> graph;
    private int numEdges;

    private final ArrayList<Vertex> visited = new ArrayList<>();

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

    public void print() {
        Set<Vertex> vertexList = graph.keySet();

        for (Vertex v : vertexList) {
            System.out.print(v + ": ");
            System.out.println(graph.get(v));
        }
    }


    public void insertEdge(String v1, String v2, String edgename) {
        Set<Vertex> vertices = graph.keySet();
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        Edge edge = new Edge(edgename);

        for (Vertex v : vertices) {
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
        }
        graph.get(vertex1).add(new GraphPairing(vertex2, edge));


        ArrayList<GraphPairing> v2EdgeList = graph.get(vertex2);
        if (v2EdgeList == null) {
            graph.put(vertex2, new ArrayList<>());
        }
        graph.get(vertex2).add(new GraphPairing(vertex1, edge));

        numEdges++;

    }

    public Set<Vertex> vertices() {
        return graph.keySet();
    }

    public Set<String> edges() {
        Set<String > allEdges = new HashSet<>();
        Set<Vertex> vertices = graph.keySet();

        for (Vertex v: vertices) {
            ArrayList<GraphPairing> gps = graph.get(v);
            if (gps != null) {
                for (GraphPairing gp: gps) {
                    allEdges.add(gp.getEdge().getName());
                }
            }
        }
        return allEdges;
    }

    public String getEdge(String v, String u) {
        Vertex v1 = getVertexFromString(v);
        Vertex v2 = getVertexFromString(u);

        ArrayList<GraphPairing> gp = graph.get(v1);
        for (GraphPairing g: gp) {
            if (g.getVertex() == v2) {
                return g.getEdge().getName();
            }
        }
        return "Edge does not exist";
    }

    public String[] endVertices(String e) {
        Set<Vertex> vertices = graph.keySet();
        int i = 0;
        String[] ans = new String[2];
        for(Vertex v: vertices) {
            ArrayList<GraphPairing> gps = graph.get(v);
            for (GraphPairing g: gps) {
                if (g.getEdge().getName().equals(e)) {
                    ans[i] = v.getName();
                    i++;
                }
            }
        }
        return ans;
    }

    public String opposite(String v, String e) {
        Vertex vertex = getVertexFromString(v);
        Edge edge = getEdgeFromString(e);

        ArrayList<GraphPairing> gps = graph.get(vertex);
        for (GraphPairing g: gps) {
            if (g.getEdge() == edge) {
                return g.getVertex().getName();
            }
        }

        return null;
    }

    public int outDegree(String v) {
        Vertex v1 = getVertexFromString(v);
        int ans = 0;

        ArrayList<GraphPairing> gps = graph.get(v1);

        for (GraphPairing g: gps) {
            if (g.getEdge() != null) {
                ans++;
            }
        }
        return ans;
    }

    public ArrayList<String> incomingEdges(String v) {
        Vertex v1 = getVertexFromString(v);
        ArrayList<String> edges = new ArrayList<>();

        ArrayList<GraphPairing> gps = graph.get(v1);
        for (GraphPairing g: gps) {
            if (g.getEdge() != null) {
                edges.add(g.getEdge().getName());
            }
        }
        return edges;

    }

    public void removeVertex(String v) {
        Vertex v1 = getVertexFromString(v);

        Set<Vertex> vertices = graph.keySet();
        //ArrayList<Vertex> list = new ArrayList<>(vertices);
        //Collections.reverse(list);

        for (Vertex vertex: vertices) {
            ArrayList<GraphPairing> gps = graph.get(vertex);
            for (int i = gps.size()-1; i>0; i--) {
                Vertex currentVertex = gps.get(i).getVertex();
                if (currentVertex.getName().equals(v1.getName())) {
                    gps.get(i).setEdge(null);
                    gps.remove(gps.get(i));
                    numEdges--;
                }
            }
        }
        vertices.remove(v1);
        numVertices--;




    }


    public void removeEdge(String e) {
        Edge edge = getEdgeFromString(e);

        Set<Vertex> vertices = graph.keySet();

        for (Vertex vertex: vertices) {
            ArrayList<GraphPairing> gps = graph.get(vertex);
            for (int i = gps.size()-1; i > 0; i--) {
                if (gps.get(i).getEdge() == edge) {
                    gps.remove(i);
                }
            }
        }
        numEdges--;



    }



    private Vertex getVertexFromString(String s) {
        Set<Vertex> vertices = graph.keySet();
        for (Vertex v : vertices) {
            if (v.getName().equals(s)) {
                return v;
            }
        }
        return null;
    }

    private Edge getEdgeFromString(String s) {
        Set<Vertex> vertices = graph.keySet();
        for (Vertex v : vertices) {
            ArrayList<GraphPairing> gps = graph.get(v);
            if (gps != null) {
                for (GraphPairing g : gps) {
                    Edge e = g.getEdge();
                    if (e.getName().equals(s)) {
                        return e;
                    }
                }
            }
        }
        return null;
    }

    public boolean doesExist(String s) {
        Vertex v1 = getVertexFromString(s);

        Set<Vertex> verticies = graph.keySet();

        return verticies.contains(v1);
    }


    public void depthFirstProcess(String v) {
        Stack<Vertex> paths = new Stack<>();
        Set<Vertex> vertices = graph.keySet();

        Vertex startingVertex = getVertexFromString(v);
        Vertex nextVertex = null;
        Vertex tempVertex;

        ArrayList<GraphPairing> gps;
        gps = graph.get(startingVertex);

        Edge currentEdge;
        /*
        if (gps.size() == 1 && gps.get(0).getEdge() != null) {
            currentEdge = gps.get(0).getEdge();
        }
        else {
            currentEdge = getEdgeFromString(findLowestNumEdge(v));
        }

         */
        visited.add(startingVertex);
        paths.push(startingVertex);


        GraphPairing graphPairing = searchForVertexNotVisited(startingVertex.getName());
        if (graphPairing != null) {
            nextVertex = graphPairing.getVertex();
            traverse(startingVertex.getName(), nextVertex.getName());
            visited.add(nextVertex);
            paths.push(nextVertex);
        }

    while(paths.size() != numVertices) {
        tempVertex = nextVertex;
        graphPairing = searchForVertexNotVisited(tempVertex.getName());
        if (graphPairing != null) {
            nextVertex = graphPairing.getVertex();
            traverse(tempVertex.getName(), nextVertex.getName());
            visited.add(nextVertex);
            paths.push(nextVertex);
        }
        else{
            paths.pop();
            nextVertex = paths.peek();
            traverse(tempVertex.getName(), nextVertex.getName());
            
        }
    }


        /*
        Vertex temp = nextVertex;
        gps = graph.get(temp);
        if (gps.size() == 1) {
            currentEdge = gps.get(0).getEdge();
        }
        else {
            currentEdge = getEdgeFromString(findLowestNumEdge(v));
        }

        nextVertex = getVertexFromString(opposite(temp.getName(), currentEdge.getName()));
        if (!visited.contains(nextVertex)) {
            traverse(startingVertex.getName(), nextVertex.getName());
        }
        visited.add(nextVertex);


         */

    }


    public void breadthFirstProcess() {

    }


    private String findLowestNumEdge(String v) {
        Vertex vertex = getVertexFromString(v);

        ArrayList<GraphPairing> gps = graph.get(vertex);

        String[] ans = new String[gps.size()];
        int index = 0;
        for (GraphPairing g: gps) {
            if (g.getEdge() != null) {
                ans[index] += g.getEdge().getName();
                index++;
            }
        }
        Arrays.sort(ans);
        return ans[0];

    }

    private void traverse(String v1, String v2) {
        Vertex vertex1 = getVertexFromString(v1);
        Vertex vertex2 = getVertexFromString(v2);
        Edge edge = getEdgeFromString(getEdge(v1, v2));


        System.out.println(vertex1.getName() + " -> " + edge.getName() + " -> " + vertex2.getName());

    }

    private GraphPairing searchForVertexNotVisited(String v) {
        Vertex vertex = getVertexFromString(v);

        ArrayList<GraphPairing> gps = graph.get(vertex);


        for (GraphPairing g: gps) {
            Vertex nextVertex = g.getVertex();
            if (!visited.contains(nextVertex)) {
                return g;
            }

        }

        return null;




    }


}
