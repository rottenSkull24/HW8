/******************************************************************
 *
 *   Jesus Ortega / 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */ 
  
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
             "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */ 
  
  public void printGraph() {
    System.out.println(
         "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * @return int - value of root or -1 if no root is found or if multiple roots.
   * @precondition - root as a node with no incoming edges.
   * @postcondition - graph is not modified.
   * 
   */
  
  public int findRoot() {
    int rootValue = -1;

    int[] incomingEdges = new int[numVertices]; // Initialize incoming edges.
    for (int i = 0; i < numVertices; i++) { 
      for (Integer dest : adjListArr[i]) { // for each destination vertex in adjacency list.
        incomingEdges[dest]++; // increment incoming edge count for destination vertex which will be used to find .
      }
    }

    int rootCount = 0; // count of roots found.
    for (int i = 0; i < numVertices; i++) { // check for vertices (a root) with no incoming edges.
      if (incomingEdges[i] == 0) {
        rootValue = vertexValues.get(i); // get the value of the root vertex.
        rootCount++; 
      }
    }
    if (rootCount != 1) { // if no root or if there are more roots are found, return -1.
      rootValue = -1;
    }

    return rootValue;
  } 
}
