import java.util.ArrayList;

/**
 *   a directed weighed graph implementation using adjacency matrix
 */
public class Graph {


    /* number of vertices: */
    private int num_vertices;
    private Node[] nodes;

    /* adjacency matrix  */
    private int adj_matrix[][];


    /**
     * Creates a graph with num_vertices, and no edges
     * @param num_vertices is the order of the graph
     */
    public Graph(int num_vertices) {
        this.num_vertices = num_vertices;

        nodes = new Node[num_vertices];

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
        adj_matrix[ vertex_start - 1 ] [ vertex_end -1 ] = weight;
    }

    /**
     * how many edges _____ from vertex_index
     * @param vertex_index
     * @return
     */
    public int out_deg(int vertex_index)
    {
        int degree=0;
        /* traverse row */
       for(int i = 0; i < num_vertices; i++)
       {
           if(adj_matrix[vertex_index-1][i] > 0)
             degree++;
       }
       return degree;
    }

    /**
     * how many edges _____ to vertex_index
     * @param vertex_index
     * @return
     */
    public int in_deg(int vertex_index)
    {
        int degree=0;
        /* traverse column:*/
        for(int i = 0; i < num_vertices; i++)
        {
            if(adj_matrix[i][vertex_index-1] > 0)
                degree++;
        }
        return degree;
    }

    /**
     *
     * @param vertex_index
     * @return
     */
    public int total_degree(int vertex_index)
    {
       return (in_deg(vertex_index) + out_deg(vertex_index));
    }


    /**
     * 
     * @param vertex_start
     * @param vertex_end
     * @return
     */
    public boolean existPath(int vertex_start, int vertex_end)
    {

    }

    /**
     *
     * @param vertexStart
     * @param vertexEnd
     * @return # de vertices del camino, 0 si no existe
     */
    public int pathLength(int vertexStart, int vertexEnd)
    {

    }




}
