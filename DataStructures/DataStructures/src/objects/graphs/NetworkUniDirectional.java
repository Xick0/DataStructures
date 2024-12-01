package objects.graphs;

import exceptions.EmptyCollectionException;
import interfaces.*;
import objects.heaps.ArrayHeap;
import objects.heaps.LinkedHeap;
import objects.lists.UnorderedArrayList;
import objects.queues.LinkedQueue;
import objects.stacks.LinkedStack;

import java.util.Iterator;

public class NetworkUniDirectional<T> implements NetworkADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected double[][] adjMatrix;
    protected T[] vertices;

    public NetworkUniDirectional() {
        numVertices = 0;
        this.vertices = (T[]) new Object[DEFAULT_CAPACITY];
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            for (int j = 0; j < DEFAULT_CAPACITY; j++) {
                adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public boolean indexIsValid(int index) {
        return (index < numVertices && index >= 0);
    }

    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix = new double[vertices.length * 2][vertices.length * 2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    public int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        int indexToRemove = getIndex(vertex);
        if (indexToRemove == -1) {
            return; // Vertex not found, exit the method
        }

        // Shift vertices left to remove the vertex
        for (int i = indexToRemove; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
        }

        // Shift rows up in the adjacency matrix
        for (int i = indexToRemove; i < numVertices - 1; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }

        // Shift columns left in the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            for (int j = indexToRemove; j < numVertices - 1; j++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1];
            }
        }

        numVertices--;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    private void addEdge(int index, int index1, int weight) {
        if (indexIsValid(index) && indexIsValid(index1)) {
            adjMatrix[index][index1] = weight;
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY; // Only one direction
        }
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        Integer x;
        QueueADT<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedListADT<T> resultList = new UnorderedArrayList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);


            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    };

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        int startIndex = getIndex(startVertex);
        Integer x;
        boolean found;
        StackADT<Integer> traversalStack = new LinkedStack<>();
        UnorderedListADT<T> resultList = new UnorderedArrayList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            try {
                x = traversalStack.peek();
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }
            found = false;


            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((adjMatrix[x][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                try {
                    traversalStack.pop();
                } catch (EmptyCollectionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        Iterator<Integer> pathIndices = iteratorShortestPathIndices(startIndex, targetIndex);
        UnorderedListADT<T> path = new UnorderedArrayList<>();

        while (pathIndices.hasNext()) {
            path.addToRear(vertices[pathIndices.next()]);
        }

        return path.iterator();
    }

    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        double[] distances = new double[numVertices];
        int[] previous = new int[numVertices];
        HeapADT<Double> heap = new ArrayHeap<>();

        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            previous[i] = -1;
        }
        distances[startIndex] = 0;
        heap.addElement((double) startIndex);

        while (!heap.isEmpty()) {
            int currentVertex = 0;
            try {
                currentVertex = heap.removeMin().intValue();
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjMatrix[currentVertex][neighbor] < Double.POSITIVE_INFINITY) {
                    double potentialDistance = distances[currentVertex] + adjMatrix[currentVertex][neighbor];
                    if (potentialDistance < distances[neighbor]) {
                        distances[neighbor] = potentialDistance;
                        previous[neighbor] = currentVertex;
                        heap.addElement((double) neighbor);
                    }
                }
            }
        }

        UnorderedListADT<Integer> path = new UnorderedArrayList<>();
        for (int vertex = targetIndex; vertex != -1; vertex = previous[vertex]) {
            path.addToFront(vertex);
        }

        if (distances[targetIndex] == Double.POSITIVE_INFINITY) {
            return new UnorderedArrayList<Integer>().iterator(); // No path exists
        }

        return path.iterator();
    }

    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY; // Return infinity if vertices are invalid
        }

        // Initialize distances to infinity and set the distance of the start vertex to 0
        double[] distances = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
        }
        distances[startIndex] = 0;

        // Initialize a priority queue to keep track of the vertices to visit
        ArrayHeap<Integer> priorityQueue = new ArrayHeap<>();
        priorityQueue.addElement(startIndex);

        // Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            int currentVertex = 0;
            try {
                currentVertex = priorityQueue.removeMin();
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }

            // Stop when we reach the target vertex
            if (currentVertex == targetIndex) {
                break;
            }

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjMatrix[currentVertex][neighbor] < Double.POSITIVE_INFINITY) {
                    double potentialDistance = distances[currentVertex] + adjMatrix[currentVertex][neighbor];

                    // If a shorter path is found, update the distances
                    if (potentialDistance < distances[neighbor]) {
                        distances[neighbor] = potentialDistance;
                        priorityQueue.addElement(neighbor);
                    }
                }
            }
        }

        // Check if a path was found
        if (distances[targetIndex] == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY; // No path exists
        } else {
            return distances[targetIndex]; // Return the weight of the shortest path
        }
    }

    @Override
    public boolean isEmpty() {
        return numVertices==0;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }
        Iterator<T> it = iteratorBFS(vertices[0]);
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (numVertices == 0) {
            result.append("Graph is empty\n");
            return result.toString();
        }

        // Print the vertices
        result.append("Vertices:\n");
        for (int i = 0; i < numVertices; i++) {
            result.append("Vertex ").append(i).append(" (").append(vertices[i].toString()).append(")\n");
        }

        // Print the adjacency matrix
        result.append("\nAdjacency Matrix:\n");
        result.append("     ");
        for (int i = 0; i < numVertices; i++) {
            result.append(String.format("%8s", vertices[i].toString()));
        }
        result.append("\n");

        for (int i = 0; i < numVertices; i++) {
            result.append(String.format("%-5s:", vertices[i].toString()));
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result.append(String.format("%8.1f", adjMatrix[i][j]));
                } else {
                    result.append(String.format("%8s", "∞"));
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    public NetworkUniDirectional<T> mstNetwork() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        HeapADT<Double> minHeap = new LinkedHeap<Double>();
        NetworkUniDirectional<T> resultGraph = new NetworkUniDirectional<T>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }
        resultGraph.adjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; i++) {
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
            resultGraph.vertices = (T[]) (new Object[numVertices]);
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        /**
         * Add all edges, which are adjacent to the starting vertex, to the heap
         */
        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(adjMatrix[0][i]);
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {
            /**
             * Get the edge with the smallest weight that has exactly one vertex
             * already in the resultGraph
             */
            do {
                try {
                    weight = minHeap.removeMin();
                } catch (EmptyCollectionException e) {
                    throw new RuntimeException(e);
                }
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }

            /**
             * Add the new edge and vertex to the resultGraph
             */
            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

            /**
             * Add all edges, that are adjacent to the newly added vertex, to
             * the heap
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }
}



