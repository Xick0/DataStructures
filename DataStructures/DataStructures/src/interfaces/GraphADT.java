package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.ElementNullException;
import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface GraphADT<T> {

    /**
     * Adds a vertex to this graph, associating object with vertex
     * @param vertex The vertex to add
     * @return The added vertex
     * @throws ElementNullException If the vertex is null
     */
    void addVertex(T vertex);

    /**
     * Removes a single vertex from this graph
     * @param vertex The vertex to remove
     * @return The removed vertex
     * @throws ElementNullException If the vertex is null
     * @throws EmptyCollectionException If this graph is empty
     * @throws ElementNotFoundException If the vertex isn't in this graph
     */
    void removeVertex(T vertex);

    /**
     * Adds an edge between two vertices of this graph
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @throws ElementNullException If the first and/or second vertex are null
     * @throws EmptyCollectionException If this graph is empty
     * @throws ElementNotFoundException If the first and/or second vertex aren't in this graph
     */
    void addEdge(T vertex1, T vertex2);

    /**
     * Removes an edge between two vertices of the graph
     * @param vertex1 The first vertex
     * @param vertex2 The second vertex
     * @throws ElementNullException If the first and/or second vertex are null
     * @throws EmptyCollectionException If this graph is empty
     * @throws ElementNotFoundException If the first and/or second vertex aren't in this graph
     */
    void removeEdge(T vertex1, T vertex2);

    /**
     * @param startVertex The starting vertex
     * @return A breadth first iterator starting with the given vertex
     * @throws ElementNullException If the vertex is null
     * @throws ElementNotFoundException If the vertex isn't in this graph
     */
    Iterator<T> iteratorBFS(T startVertex);

    /**
     * @param startVertex The starting vertex
     * @return A depth first iterator starting with the given vertex
     * @throws ElementNullException If the vertex is null
     * @throws ElementNotFoundException If the vertex isn't in this graph
     */
    Iterator<T> iteratorDFS(T startVertex);

    /**
     * @param startVertex The starting vertex
     * @param targetVertex The target vertex
     * @return An iterator that contains the shortest path between the two vertices
     * @throws ElementNullException If the starting and/or the targetVertex are null
     * @throws ElementNotFoundException If the starting and/or the targetVertex aren't in this graph
     */
    Iterator<T> iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Checks if this graph is empty
     * @return A boolean value indicating if this graph is empty or not
     */
    boolean isEmpty();

    /**
     * @return True if this graph is connected, false otherwise
     */
    boolean isConnected();

    /**
     * @return This graph's size
     */
    int size();

    /**
     * @return A string representation of the adjacency matrix
     */
    @Override String toString();
}
