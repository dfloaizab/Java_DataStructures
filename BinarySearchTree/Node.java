public class Node<E> {

    /* fields */
    E value;  /* value or data of the node */
    int key;
    Node left;
    Node right;


    /* methods */

    public Node() {
    }

    public Node(E value) {
        this.value = value;
    }
    
    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int newKey)
    {
        return key;
    }

    public void setValue(E value) {
        this.value = value;
    }

    
    public Node getLeft()
    {
        return this.left;
    }
    
    public Node getRight()
    {
        return this.right;
    }

    public void setLeft(Node newLeft) {
        this.left = left;
    }
    
       public void setRight(Node newLeft) {
        this.right = right;
    }

}
