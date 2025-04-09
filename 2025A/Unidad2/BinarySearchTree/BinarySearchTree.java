class BinarySearchTree {


    Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    //Operaciones básicas (inserción y búsqueda)
    /**
     * Inserción de un nodo en un árbol binario de búsqueda, aplicando la estrategia de dividir y vencer
     * de tal forma que el nodo quede en la posición correcta, dejando el árbol ordenado
     * @param root
     * @param value
     * @return
     */
    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    /**
     * Búsqueda de un nodo en el BST de acuerdo al valor:
     * @param root
     * @param value
     * @return
     */
    private Node searchNode(Node root, int value)
    {
        return null;
    }

    //Recorridos:


}
