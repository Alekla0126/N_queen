import java.util.EmptyStackException;
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
/**
 *
 * @author Alejandro Lagunes
 */
// ---------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
public class LinkedStack<E> implements Cloneable
{
   // ------------------------------------------------------------------------------------------------------------------
   // ------------------------------------------------------------------------------------------------------------------
   // Invariant of the LinkedStack class:
   //   1. The items in the stack are stored in a linked list, with the top of
   //      the stack stored at the head node, down to the bottom of the stack
   //      at the final node.
   //   2. The instance variable top is the head reference of the linked list
   //      of items.
   // ------------------------------------------------------------------------------------------------------------------
   // ------------------------------------------------------------------------------------------------------------------
   // ------------------------------------------------------------------------------------------------------------------
   private Node<E> top;
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Initialize an empty stack.
    **/
   public LinkedStack( )
   {
      top = null;
   }
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Generate a copy of this stack.
   * @return
   *   The return value is a copy of this stack. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public LinkedStack<E> clone( )       
   {
      LinkedStack<E> answer;
      try
      {
         answer = (LinkedStack<E>) super.clone( );
      }
      catch (CloneNotSupportedException e)
      { 
         throw new RuntimeException
         ("This class does not implement Cloneable");
     }
      // The generic listCopy method gets the type of E from top.
      answer.top = Node.listCopy(top);
      return answer;
   }
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Get the top item, removing it from this stack.
   * @param - none
   * Precondition: This stack is not empty.
   * Post-condition: The return value is the top item of this stack, and the item has been removed.
   * @exception EmptyStackException Indicates that this stack is empty.
   **/    
   public E pop( )
   {
      E answer;
      
      if (top == null)
         // EmptyStackException is from java.util and its constructor has no argument.
         throw new EmptyStackException( );
      
      answer = top.getData( );
      top = top.getLink( );
      return answer;
   }
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Push a new item onto this stack. The new item may be the null
   * reference.
   * @param item the item to be pushed onto this stack
   * Post-condition: The item has been pushed onto this stack.
   * @exception OutOfMemoryError Indicates insufficient memory for increasing the stack's capacity.
   **/    
   public void push(E item)
   {
      top = new Node<E>(item, top);
   }
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Accessor method to determine the number of items in this stack.
   * @param - none
   * @return: the number of items in this stack
   **/ 
   public int size( )   
   {
      // The generic listLength method gets the type of E from top.
      return Node.listLength(top);
   }
   // ------------------------------------------------------------------------------------------------------------------
   /**
   * Search for and return an item within the LinkedStack 
   *
   * @param int n;
   * the item "n" amount away from the top.
   * @return E answer the item at that level.
   * @precondition: the object called is not null, and the stack is not empty.
   **/                  
   public E itemAt(int n)
   {
      E answer;
      Node<E> current = top;
      int index = 0;
      while(index < n)
      {
         current = current.getLink();
         index++;
      }
      answer = current.getData( );
      if(answer == null)
      {
         throw new EmptyStackException();
      }
      return answer;
   }
   // ------------------------------------------------------------------------------------------------------------------
}