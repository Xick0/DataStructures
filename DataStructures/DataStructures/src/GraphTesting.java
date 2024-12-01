import interfaces.GraphADT;
import objects.graphs.Graph;

import java.util.Iterator;

public class GraphTesting {
    public static void main(String[] args) {
        GraphADT<String> grafao = new Graph<>();

        grafao.addVertex("Felgueiras");
        grafao.addVertex("Mexico");
        grafao.addVertex("Angola");
        grafao.addVertex("BRAZIUUUUUU");
        grafao.addVertex("Goundogan");
        grafao.addEdge("Felgueiras", "Mexico");
        grafao.addEdge("Goundogan", "Mexico");
        grafao.addEdge("BRAZIUUUUUU", "Angola");
        grafao.addEdge("BRAZIUUUUUU", "Goundogan");
        grafao.addEdge("Felgueiras", "Angola");
        grafao.addEdge("Goundogan", "Angola");

        System.out.println(grafao);


        Iterator<String> it = grafao.iteratorBFS("Felgueiras");
        while (it.hasNext()) {
            System.out.println("IteratorBFS" + it.next());
        }

        System.out.println(grafao.isConnected());

        Iterator<String> it2 = grafao.iteratorDFS("Felgueiras");
        while (it2.hasNext()) {
            System.out.println("IteratorBFS " + it2.next());
        }

        Iterator<String> it3 = grafao.iteratorShortestPath("Felgueiras", "Goundogan");
        while (it3.hasNext()) {
            System.out.println("Shortest Path " + it3.next());
        }
    }
}