/**
 * A Singly Linked List (Dynamic) implementation in java
 * Diego Loaiza 2021
 */
public class SinglyLinkedList<T> {

    //attributes
    private ListNode head;
    private ListNode tail;
    private int size;

    public static void main(String args[])
    {
        //create the list
        SinglyLinkedList myFirstList = new SinglyLinkedList();

        //add some nodes...
        myFirstList.appendNode(new ListNode("Diego "));
        System.out.println("nodo agregado al final");
        myFirstList.appendNode(new ListNode("Loaiza "));
        System.out.println("nodo agregado al final");
        myFirstList.appendNode(new ListNode("Buitrago "));
        System.out.println("nodo agregado al final");
        myFirstList.addFirst(new ListNode("Don "));
        System.out.println("nodo agregado al inicio");
        myFirstList.printList();
        System.out.println("\n");
        try {
            myFirstList.insertNode(new ListNode("Fernando"), 1);
        }catch(Exception e){}

        //print the list
        myFirstList.printList();

    }

    // METHODS , OPERATIONS
    /**
     * 1. create an empty list
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    // operations on the list

    /**
     * determine - whether (si) / if (si) -  list is empty or not
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty()
    {
        return(head == null || tail == null);
    }

    /**
     * add a node at the end of the list
     * @param nodeToInsert
     */
    void appendNode(ListNode nodeToInsert)
    {
       
    }

    /**
     * add node at the beginning of the list
     * @param newNode
     */
    void addFirst(ListNode newNode)
    {
       
    }

    /**
     * Insert a node in the list at a given position
     * @param newNode node to insert
     * @param pos position of new node
     */
    void insertNode(  ListNode newNode, int pos  ) throws Exception
    {
        

    }


    /**
     * print the content of the list (the data of each node)
     */
    void printList()
    {
        //current node
        ListNode currentNode = head;
        while(  currentNode != null  )
        {
            //write content of the node
            System.out.print(currentNode.getData()+"->");

            //update current node position
            currentNode = currentNode.getNext();
        }
    }

    /**
     * TODO
     * delete a node from the list given its position
     * @param pos
     */
    void deleteNode(int pos)
    {
        //if list is empty, error
        //if list is not empty, validate position
        //if position is invalid, error
        //if position is valid,
        //traverse list to reach pos
        //update references in order to isolate node at pos (previous node)
    }

    /** TODO
     * Query a node in a given position
     * @param pos
     * @return the node
     */
    ListNode getNode(int pos)
    {
       //
       ListNode theNode = null;
       //
       //traverse the list to reach the position of the node to query:
       //
        return theNode;
    }


}
