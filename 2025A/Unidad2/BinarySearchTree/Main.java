public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.inorder();    // 20 30 40 50 60 70 80
        bst.preorder();   // 50 30 20 40 70 60 80
        bst.postorder();  // 20 40 30 60 80 70 50

        System.out.println("¿Existe 40?: " + bst.search(40)); // true
        System.out.println("¿Existe 99?: " + bst.search(99)); // false
    }
}
