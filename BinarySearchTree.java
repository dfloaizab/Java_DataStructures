public class BinarySearchTree<E> {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int data) {

        root = new Node(data);
    }

    public Node getRoot()
    {
        return this.root;
    }

    /**
     * R - Insertar nodo en el árbol, de forma recursiva, conservando el ordenamiento
     * @param
     */
    public void insertNode(Node nodeToInsert)
    {
        root = insertRecursive(nodeToInsert, root);
    }

    /**
     *
     * @param nodeToInsert nodo a insertar
     * @param rootNode punto de inserción del nodo nuevo. Nodo raíz del sub-arbol donde se va a insertar.
     * @return
     */
    public Node insertRecursive(Node nodeToInsert, Node rootNode)
    {
        //Caso base:
        //1. árbol vacío:
        if(rootNode == null)
            return nodeToInsert;

        //Casos recursivos:
        //Insertar a la izquierda si el valor del nuevo nodo es
        // menor que el valor del nodo raíz a partir del cual va a insertar:
        if( (int) nodeToInsert.getData() <  (int) rootNode.getData() )
        {
            rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        }
        else
        {
            //Insertar a la derecha, si el valor del nuevo nodo es
            // mayor que el valor del nodo raíz a partir del cual voy a insertar:
            if( (int) nodeToInsert.getData() >  (int) rootNode.getData())
            {
               rootNode.setRight(insertRecursive(nodeToInsert,rootNode.getRight() ));
            }
            else // Tiene un valor igual (no hay duplicados)
            {
                // ¿Qué retorna?
            }
        }
        return rootNode;
    }

    /**
     * R
     * @param key
     * @return
     */


    public boolean searchNode(int key)
    {

        return searchNodeRecursiveB(key, root);
    }

    public boolean searchNodeRecursiveB(int key, Node current)
    {
        boolean result;
        //caso base para la búsqueda:



        //llamados recursivos:


    }



    /**
     * R
     * @param current
     */
    public void traverseInOrder(Node current)
    {
        if(current != null) {
            //visita de la rama izquierda:
            traverseInOrder(current.getLeft());
            //visita del nodo raíz del recorrido (nodo actual)
            System.out.print(current.getData());
            //visita de la rama derecha:
            traverseInOrder(current.getRight());
        }
    }


    /**
     * R
     * @param current
     */

    public void traversePostOrder(Node current)
    {
        if(current != null) {
            traversePostOrder(current.getLeft());
            traversePostOrder(current.getRight());

            //visito el nodo raíz del recorrido de último:
            System.out.print(current.getData());
        }
    }


    /**
     * R
     * @param current
     */

    public void traversePreOrder(Node current)
    {
        if(current != null) {
            System.out.print(current.getData());
            traversePreOrder(current.getLeft());
            traversePreOrder(current.getRight());
        }
    }






}
