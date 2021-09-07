public class Stack<E>
{


    Node top;
    int size;

    public Stack() {
         top = null;
         size = 0;
    }

    /**
     * add a node at the top of the stack
     */
    public void push(  Node newNode   )
    {
        size++;
        if(isEmpty())
        {
           top = newNode;
        }
        else
        {
            newNode.setNext( top );
            top = newNode;
        }
    }

    public boolean isEmpty()
    {
        return (top == null);
    }

    /**
     * return the node at the top of the stack
     * @return
     */
    public Node pop()
    {
       Node nodeToReturn = new Node();
       if(!isEmpty())
       {
          nodeToReturn = top;
          /**/
          top.setNext(top.getNext());
          size--;
          /**/
       }
       return nodeToReturn;
    }

    /**
     * return a copy of the top of the stack
     * @return
     */
    public Node peek()
    {
      return(top);
    }


}
