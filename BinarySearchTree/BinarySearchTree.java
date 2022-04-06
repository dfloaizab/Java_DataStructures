import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;
    ArrayList<Integer> keyList;

    public ArrayList<Integer> getKeyList()
    {
        //construir la lista ordenada desde el árbol usando el
        //recorrido apropiado:


        //retornar la lista:
        return keyList;
    }

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

    //Inserción de un nodo:
    public void insertNode(Node newNode)
    {
        insertRecursive(newNode, root);
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

    /**
     * recorre el árbol en in-orden:
     *      1. Primero visita la rama izquierda
     *      2. Luego "Visita" el nodo raíz del recorrido
     *      3. Finalmente visita la rama derecha
     * @param root raiz del recorrido
     */
    public void InOrder( Node root )
    {
        //caso base:
        if(root == null)
            return;
        //llamados recursivos:
        else
        {
            //1. recorro toda la rama izquierda
            InOrder(root.getLeft());
            //2. visito el nodo raíz:
            System.out.println("->"+root.getKey()); //
            //3. recorro toda la rama derecha:
            InOrder(root.getRight());
        }
    }

    public void preOrder(Node root)
    {
      //caso base (halting condition):
      if(root == null)
          return;
      else
      {
          //1. visita el nodo root:
          System.out.println("->"+root.getKey());
          //2. recorre en preorden, la rama izquierda:
          preOrder(root.getLeft());
          //3. recorre en preorden la rama derecha:
          preOrder(root.getRight());
      }
    }

    public void posOrder(Node root)
    {
        if(root == null)
            return;
        else
        {
           posOrder(root.getLeft());
           posOrder(root.getRight());
           System.out.println("->"+root.getKey());
        }
    }

}
