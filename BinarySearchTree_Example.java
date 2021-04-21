


public class BinarySearchTree_Example {


    public static void main(String args[])
    {

        BinarySearchTree myBST = new BinarySearchTree();

        myBST.insertNode(new Node(5));
        myBST.insertNode(new Node(8));
        myBST.insertNode(new Node(3));
        myBST.insertNode(new Node(2));
        myBST.insertNode(new Node(1));

        System.out.print("\nRecorrido en pre-orden");
        myBST.traversePreOrder(myBST.getRoot());

        System.out.print("\nRecorrido en post-orden");
        myBST.traversePostOrder(myBST.getRoot());

        System.out.print("\nRecorrido en in-orden");
        myBST.traverseInOrder(myBST.getRoot());

    }


}
