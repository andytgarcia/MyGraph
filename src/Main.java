import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyGraph g = new MyGraph();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");
        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("J");
        g.insertVertex("K");
        g.insertVertex("L");
        g.insertVertex("M");
        g.insertVertex("N");
        g.insertVertex("O");
        g.insertVertex("P");




        g.insertEdge("A", "B", "1");
        g.insertEdge("C", "D", "2");
        g.insertEdge("A", "F", "3");
        g.insertEdge("B", "G", "4");
        g.insertEdge("D", "G", "5");
        g.insertEdge("D", "H", "6");
        g.insertEdge("E", "F", "7");
        g.insertEdge("G", "H", "8");
        g.insertEdge("E", "I", "9");
        g.insertEdge("E", "J", "10");
        g.insertEdge("G", "J", "11");
        g.insertEdge("G", "K", "12");
        g.insertEdge("H", "L", "13");
        g.insertEdge("I", "J", "14");
        g.insertEdge("K", "L", "15");
        g.insertEdge("I", "M", "16");
        g.insertEdge("J", "N", "17");
        g.insertEdge("K", "P", "18");
        g.insertEdge("L", "P", "19");
        g.insertEdge("M", "N", "20");
        g.insertEdge("O", "P", "21");






        g.print();



        System.out.println("Num Vertices: " + g.getNumVertices());
        System.out.println("Num Edges: " + g.getNumEdges());


        System.out.println("Edge between A and B: " + g.getEdge("A", "B"));
        System.out.println("Edge between K and L: " + g.getEdge("K", "L"));

        System.out.println("Vertices for edge 1: " + Arrays.toString(g.endVertices("1")));
        System.out.println("Vertices for edge 15: " + Arrays.toString(g.endVertices("15")));

        System.out.println("Opposite vertex from A and edge 1: " + g.opposite("A", "1"));
        System.out.println("Opposite Vertex of G and 11 is " + g.opposite("G", "11"));


        System.out.println("All Edges connected to G: " + g.outDegree("G") );
        System.out.println("All edges connected to L: " + g.outDegree("L"));

        System.out.println("List of edges connected to G: " + g.incomingEdges("G"));
        System.out.println("List of edges connected to N: " + g.incomingEdges("N"));

        System.out.println(g.doesExist("G"));

        g.removeVertex("G");
        g.print();
        
        System.out.println(g.doesExist("G"));

        g.removeEdge("2");
        g.print();




    }
}
