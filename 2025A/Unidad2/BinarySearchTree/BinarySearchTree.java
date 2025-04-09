class BinarySearchTree {


    Node root;

    /**
     * Crea un BST vacío
     */
    public BinarySearchTree()
    {
        root = null;
    }

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
        //caso base:
        if (root == null) {
            return new Node(value);
        }
        //dividir y vencer: llamados recursivos
        //para un BST ordenado de forma ascendente
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

    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (value == root.value) {
            return true;
        }
        //similar al algoritmo de búsqueda binaria!!
        return (value < root.value)
            ? searchRec(root.left, value)
            : searchRec(root.right, value);
    }

    //Recorridos:

    public void preorder() {
        System.out.print("Preorden: ");
        preorderRec(root);
        System.out.println();
    }

    public void postorder() {
        System.out.print("Postorden: ");
        postorderRec(root);
        System.out.println();
    }

    public void inorder() {
        System.out.print("Inorden: ");
        inorderRec(root);
        System.out.println();
    }

    /**
     * recorrido inOrden:
     * @param root
     */
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    private void preorderRec(Node root) {
        if (root != null) {
            //procesar nodo raiz del recorrido
            System.out.print(root.value + " ");
            //recorrer recursivamente la rama izquierda:
            preorderRec(root.left);
            //recorrer recursivamente la rama derecha:
            preorderRec(root.right);
        }
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }



}
