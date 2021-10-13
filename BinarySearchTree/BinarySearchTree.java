
public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node getRoot()
    {
        return root;
    }

    /* operaciones de la estructura de datos: */
    //¿Este método permite nodos con llave duplicada?
    public Node insertRecursive(Node nodeToInsert, Node rootNode)
    {
       //condición de parada:
        //encontrar el punto de inserción: la derecha o la izquierda de una hoja, que es null
        if(rootNode == null)
            return nodeToInsert;

        //llamados recursivos:
        if ( (int) nodeToInsert.getKey() < (int) rootNode.getKey())
               rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        else
            if( (int) nodeToInsert.getKey() > (int) rootNode.getKey())
                rootNode.setRight(insertRecursive(nodeToInsert, rootNode.getRight()));

        return rootNode;
    }

    public boolean searchNodeRecursive( int key, Node rootNode  )
    {
        boolean result = false;
       if(rootNode == null)
           result =  false;
       else {
           if (rootNode.getKey() == key)
               result = true;
           else {
               if (rootNode.getKey() > key)
                   result = searchNodeRecursive(key, rootNode.getLeft());
               else if (rootNode.getKey() < key)
                   result = searchNodeRecursive(key, rootNode.getRight());

           }
       }
       return result;
    }

}
