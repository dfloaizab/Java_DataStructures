/**
 * A Singly Linked List (Dynamic) implementation in java
 * Diego Loaiza 2021
 */
public class SinglyLinkedList<T> {

    /** attributes **/
    //reference to head of the list (first node):
    private ListNode head;
    //reference to tail of the list (last node):
    private ListNode tail;
    //size of the list as number of elements:
    private int size;



    /** Methods: operations on the list **/

    /**
     * Constructor method: create an empty list
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    /** operations on the list **/

    /**
     * determine - if  list is empty or not
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty()
    {
        return(head == null || tail == null);
    }

    /**
     * TODO: Complete Method
     * add a node at the end of the list
     * @param nodeToInsert
     */
    void appendNode(ListNode nodeToInsert)
    {
        //list is empty:
        if( isEmpty() )
        {
            head = newNode;
            tail = newNode;
        }
        else //list is not empty:
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * TODO: Complete Method
     * Insert node in the list at a given position
     * @param newNode node to insert
     * @param pos position of new node
     */
    void insertNode(  ListNode newNode, int pos  ) throws Exception
    {

        ListNode current = head;
        int count = 0;
        if(pos >=0 && pos <= size)
        {
            if(pos == 0)
            {
                newNode.setNext(head);
                head = newNode;
                size++;
            }
            else
            {
                if(pos == size)
                {
                    appendNode(newNode);
                }
                else {
                    while (count < pos - 1) {
                        current = current.getNext();
                        count++;
                    }
                    newNode.setNext(current.getNext());
                    current.setNext(newNode);
                    size++;
                }
            }
        }
    }


    /**
     * print the content of the list (the data of each node)
     */
    void printList()
    {
        //current node start at head of the list
        ListNode currentNode = head;
        System.out.print("\n");
        //traverse list
        while(  currentNode != null  )
        {
            //write content of the node
            System.out.print(currentNode.getData()+"->");

            //update current node position
            currentNode = currentNode.getNext();
        }
    }

    /**
     * return the number of nodes of the list
     * @return
     */
    int getNumElements()
    {
        if(isEmpty())
            return 0;

        ListNode currentNode = head;
        int count = 1;
        while(  currentNode != tail  )
        {
            //update current node position
            currentNode = currentNode.getNext();
            count++;
        }
        return count;
    }

    /**
     * TODO: Complete method
     * delete a node from the list given its position
     * @param pos
     */
    void deleteNode(int pos)
    {

        //1. recorrer la lista hasta pos - 1
        ListNode currentNode = head;
        int count = 0;
        while(count < pos)
        {
            currentNode = currentNode.getNext();
        }
        //2. des-referenciar o aislar el nodo que sigue
        currentNode.setNext(   currentNode.getNext().getNext()  );

    }

    boolean posIsValid(int pos)
    {
        return (pos >=0 && pos < size);
    }

    /**
     * Query a node in a given position
     * @param pos
     * @return the node
     */
    ListNode getNode(int pos)
    {
       //
       ListNode theNode = null;
       //
       //
        return theNode;
    }


}
