import java.util.ArrayList;

public class Node {

    private int data; //Key

    //árbol binario
    private Node left; //rama izquierda (sub-árbol) : left es la raiz del sub-arbol izquierdo
    private Node right; //rama derecha (sub-árbol) : right es la raiz del sub-árbol derecho



    public Node() {
        left = null;
        right = null;
    }

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
