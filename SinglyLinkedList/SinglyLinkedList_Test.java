public class SinglyLinkedList_Test {

    public static void main(String args[])
    {
        //create the list
        SinglyLinkedList myFirstList = new SinglyLinkedList();
        SinglyLinkedList Department = new SinglyLinkedList();

        Department.appendNode(new ListNode(new Office("Pance")));

        //add some nodes...
        myFirstList.appendNode(new ListNode("Diego "));
        System.out.println("nodo agregado al final");
        myFirstList.appendNode(new ListNode("Loaiza "));
        System.out.println("nodo agregado al final");
        myFirstList.appendNode(new ListNode("Buitrago "));
        System.out.println("nodo agregado al final");
        System.out.println("nodo agregado al inicio");
        System.out.println("Lista de Cadenas:");
        myFirstList.printList();
        System.out.println("Numero de elementos: "+myFirstList.getNumElements());

        System.out.println("Lista de Reales:");
        SinglyLinkedList myFirstRealList = new SinglyLinkedList();
        myFirstRealList.printList();
        System.out.println("Numero de elementos: "+myFirstRealList.getNumElements());


    }

}
