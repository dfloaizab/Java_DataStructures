public class BinarySearchTree_Prueba
{

    public static void main(String[] args)
    {

        /* probar implementación del árbol binario de búsqueda */
        BinarySearchTree myFirstBinarySearchTree = new BinarySearchTree(new Node(5,'F'));

        myFirstBinarySearchTree.insertRecursive(new Node(8,"J"), myFirstBinarySearchTree.getRoot());

        myFirstBinarySearchTree.searchNodeRecursive(4, myFirstBinarySearchTree.getRoot());
        myFirstBinarySearchTree.searchNodeRecursive(5, myFirstBinarySearchTree.getRoot());

        long startTime  = System.currentTimeMillis() ;
        myFirstBinarySearchTree.insertRecursive( new Node(3,"C"), myFirstBinarySearchTree.getRoot() );
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

    }

}
