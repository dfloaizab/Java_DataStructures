import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

/**
 *   a directed graph implementation using adjacency matrix
 */
public class Graph {


    /* number of vertices: */
    private int num_vertices;

    /* list of vertices*/
    ArrayList<GraphNode> vertices;

    /* adjacency matrix  */
    private int adj_matrix[][];

    /* node lists */



    public Graph(int num_vertices) {
        this.num_vertices = num_vertices;
        /* initialize adjacency matrix: */
        adj_matrix = new int[num_vertices][num_vertices];
        for(int i = 0; i < num_vertices; i++)
        {
            for(int j = 0; j < num_vertices; j++)
            {
                adj_matrix[i][j] = 0;
            }
        }
    }

    /**
     * add and edge from vertex_start from vertex_end
     * @param vertex_start index of starting vertex
     * @param vertex_end index of end vertex
     * @param weight weight of the edge (arc)
     */
    public void add_edge(int vertex_start, int vertex_end, int weight)
    {

    }

    /**
     * how many edges _____ from vertex_index
     * @param vertex_index
     * @return
     */
    public int out_deg(int vertex_index)
    {

    }

    /**
     * how many edges _____ to vertex_index
     * @param vertex_index
     * @return
     */
    public int in_deg(int vertex_index)
    {

    }

    /**
     *
     * @param vertex_index
     * @return
     */
    public int total_degree(int vertex_index)
    {

    }


    /**
     *
      * @param vertex1 index of first vertex
     * @param vertex2 index of second vertex
     * @return true if there is a path between two vertices, false otherwise
     */
    public boolean isPath(int vertex1, int vertex2)
    {

    }



}
