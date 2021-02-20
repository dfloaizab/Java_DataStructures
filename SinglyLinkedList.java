/**
 * A Singly Linked List implementation in java
 * Diego Loaiza 2021
 */
public class SinglyLinkedList {

    //attributes
    private ListNode head;
    private ListNode tail;

    // methods

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public SinglyLinkedList(ListNode head, ListNode tail) {
        this.head = head;
        this.tail = tail;
    }

    // operations on the list

    /**
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty()
    {
        return(head == null || tail == null);
    }

    /**
     * insert a node at the end of the list
     * @param nodeToInsert
     */
    void appendNode(ListNode nodeToInsert)
    {
        if(isEmpty()) {
            head = nodeToInsert;
        } else tail.setNext(nodeToInsert);
        tail = nodeToInsert;
    }

    /**
     * Insert a node in the list at a given position
     * @param newNode node to insert
     * @param pos position of new node
     */
    void insertNode(  ListNode newNode, int pos  ) throws Exception
    {
        //list is empty:
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            //list is not empty, position is invalid ( < 0, or > num. of nodes  ):
            if(pos < 0 || pos > size-1)
            {
               throw new Exception("index out of bounds");
            }
            //list is not empty, position is valid:
            else {
                //find position to insert the node
                ListNode currentNode = head;
                int currentPos = 0;
                //traverse list to find the position of insertion:
                while( currentPos < pos  )
                {
                    //update current node position: move through the list
                    currentNode = currentNode.getNext();
                    //update current position
                    currentPos++;
                }
                //update references of new node:
                newNode.setNext(currentNode.getNext()); //A
                currentNode.setNext(newNode); //B
            }

        }

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
       //
        return theNode;
    }


}
