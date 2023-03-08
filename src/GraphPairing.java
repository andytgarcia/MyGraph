public class GraphPairing {
    private Vertex v;
    private Edge e;

    public GraphPairing(Vertex v, Edge e) {
        this.v = v;
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
