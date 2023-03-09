public class GraphPairing {
    private Vertex v;
    private Edge e;

    public GraphPairing(Vertex v, Edge e) {
        this.v = v;
        this.e = e;
    }

    public Vertex getVertex() {
        return v;
    }

    public void setVertex(Vertex v) {
        this.v = v;
    }

    public Edge getEdge() {
        return e;
    }

    public void setEdge(Edge e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "GraphPairing{" +
                "v=" + v +
                ", e=" + e +
                '}';
    }
}
