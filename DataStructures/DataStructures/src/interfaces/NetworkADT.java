package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

public interface NetworkADT<T> extends GraphADT<T> {
    /**
     * Adds an edge between two vertices of this network
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @param weight The weight
     * @throws ElementNullException If the first and/or second vertex are null
     * @throws EmptyCollectionException If this network is empty
     * @throws ElementNotFoundException If the first and/or second vertex aren't in this network
     */
    void addEdge(T vertex1, T vertex2, double weight) ;

    /**
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @return The weight of the shortest path in this network
     * @throws ElementNullException If the first and/or second vertex are null
     * @throws EmptyCollectionException If this network is empty
     * @throws ElementNotFoundException If the first and/or second vertex aren't in this network
     */
    double shortestPathWeight(T vertex1, T vertex2);
}
