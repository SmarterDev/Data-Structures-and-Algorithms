//Ryan Leonard
//OrderedLinkedList.java
//10-24-2016

package dataStructures;

/**
 *	Class OrderedLinkedList.
 *
 *	This class functions as a linked list, but ensures items are stored in ascending order.
 *
 */
 
public class OrderedLinkedList
{
	
	/**************************************************************************
	 * Constants
	 *************************************************************************/
	
	/** return value for unsuccessful searches */
	private static final OrderedListNode NOT_FOUND = null;
	

	/**************************************************************************
	 * Attributes
	 *************************************************************************/

	/** current number of items in list */
	private int theSize;
	
	/** reference to list header node */
	private OrderedListNode head;
	
	/** reference to list tail node */
	private OrderedListNode tail;
	
	/** current number of modifications to list */
	private int modCount;
	
	
	/**************************************************************************
	 * Constructors
	 *************************************************************************/

	
	/**
	 *	Create an instance of OrderedLinkedList.
	 *
	 */
	public OrderedLinkedList()
	{
		// empty this OrderedLinkedList
		clear();
	}
	
	
	/**************************************************************************
	 * Methods
	 *************************************************************************/


	/*
	 *	Add the specified item to this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be added
	 */
	public boolean add(Comparable obj)
	{
		// TODO: Implement this method (8 points)
		
		//Creates added node and current node
		OrderedListNode currentNode = head.next;
		OrderedListNode temp = null;
      
		//Checks to see if list is empty
		if (isEmpty())
		{
         //Adds new node 
			OrderedListNode addedNode = new OrderedListNode(obj, head, tail);
			
			//Updates pointers
			head.next = addedNode;
         addedNode.previous = head;
         addedNode.next = tail;
         tail.previous = addedNode;
         
         //Updates modcount and currentNode
         modCount++;
         theSize++;
         
         return true;
		} else
      {
   		//Adds items into orderedlist while not at tail node 
   		while(currentNode != tail)
   		{			
   			
   			//Adds item if equal to or greater than current node but less than next node 
   			if (obj.compareTo(currentNode.theItem) >= 0 && obj.compareTo(currentNode.next.theItem) <= 0)
   			{
               //Stores values of next node 
   				temp = currentNode.next;
   				
   				//Inserts node into position after current 
   				OrderedListNode addedNode = new OrderedListNode(obj, currentNode, currentNode.next);
   					
   				//Updates pointers
   				currentNode.next = addedNode;
               addedNode.previous = currentNode;
               addedNode.next = temp;
               temp.previous = addedNode;
               
               //Updates modcount and size
               modCount++;
   				theSize++;
               
               return true;
   			
   			} 
   			
   			//If item less than current node, add before current node  
   			else if (obj.compareTo(currentNode.theItem) < 0)
   			{
   				//Inserts node into position after current 
   				OrderedListNode addedNode = new OrderedListNode(obj, currentNode, currentNode.next);
   				
   				//Updates pointers
   				head.next = addedNode;
               addedNode.previous = head;
   				addedNode.next = currentNode;
               currentNode.previous = addedNode;
   				
               //Updates modcount and size 
               modCount++;
               theSize++;
               
               return true;
   			}
            
            //Adds item after head but before current item 
   			else if (currentNode == head && obj.compareTo(currentNode.next.theItem) < 0)
   			{
   				//Inserts node into position after current 
   				OrderedListNode addedNode = new OrderedListNode(obj, head, currentNode);
   				
   				//Updates pointers
   				head.next = addedNode;
               addedNode.previous = head;
   				addedNode.next = currentNode;
               currentNode.previous = addedNode;
   				
               //Updates modcount and size 
               modCount++;
               theSize++;
               
               return true;
   			}
            
            //Adds item after between node and tail 
   			else if (obj.compareTo(currentNode.theItem) >= 0 && currentNode.next.theItem == tail)
   			{
   				//Inserts node into position after current 
   				OrderedListNode addedNode = new OrderedListNode(obj, currentNode, tail);
   				
   				//Updates pointers
               currentNode.next = addedNode;
   				addedNode.previous = currentNode;
   				addedNode.next = tail;
               tail.previous = addedNode;
   				
               //Updates modcount and size
               modCount++;
   				theSize++;
               
               return true;
   				
   			}
            
            //Increments current node 
            currentNode = currentNode.next;
             
   		}//Ends while loop 
      }
      
      //Checks to see if tail is reached
		if (currentNode == tail)
		{      
         //Goes back to last node in list 
         currentNode = currentNode.previous;

         //Adds new node 
			OrderedListNode addedNode = new OrderedListNode(obj, currentNode, tail);
         
         //Updates pointers
		   currentNode.next = addedNode;
         addedNode.previous = currentNode;
         addedNode.next = tail;
         tail.previous = addedNode;
         
         //Updates modcount and currentNode
         modCount++;
         theSize++;
         
			return true;
		}
      
   return false;
     
	}//Ends add method 
   
	/*
	 *	Remove the first occurrence of the specified item from this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be removed
	 */
	public boolean remove(Comparable obj)
	{
		// TODO: implement this method (7 points)
		
      OrderedListNode currentNode = head.next;
      
      //Checks to see if list is empty 
		if(isEmpty())
      {
         return false;
      }
      
      //While not at tail, keep checking  
      while(currentNode != tail)
      {
         //If comparison is equal, remove item  
         if(obj.equals(currentNode.theItem))
         {
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            return true;  
         }
         //Increment current node being checked 
         currentNode = currentNode.next;
      }
       
      //Updates modcount and size 
      ++modCount;
      theSize--;
      
      return false;
	}

	
	/**
	 *	Empty this OrderedLinkedList.
	 */
	public void clear()
	{
		// reset header node
		head = new OrderedListNode("HEAD", null, null);

		// reset tail node
		tail = new OrderedListNode("TAIL", head, null);

		// header references tail in an empty LinkedList
		head.next = tail;

		// reset size to 0
		theSize = 0;

		// emptying list counts as a modification
		modCount++;
	}


	/**
	 *	Return true if this OrderedLinkedList contains 0 items.
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}


	/**
	 *	Return the number of items in this OrderedLinkedList.
	 */
	public int size()
	{
		return theSize;
	}
	

	/*	
	 *	Return a String representation of this OrderedLinkedList.
	 *
	 *	(non-Javadoc)
     *	@see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
    	String s = "";
    	
    	OrderedListNode currentNode = head.next;
    	
    	while (currentNode != tail)
    	{
    		s += currentNode.theItem.toString();
    		
    		if (currentNode.next != tail)
    		{
    			s += ", ";
    		}
    		
    		currentNode = currentNode.next;
    	}
    	
    	return s;
    }

	
	/**************************************************************************
	 * Inner Classes
	 *************************************************************************/
	

	/**
	 *	Nested class OrderedListNode.
	 *
	 *	Encapsulates the fundamental building block of an OrderedLinkedList
	 *	contains a data item, and references to both the next and previous nodes
	 *	in the list
	 */
	
	
	// TODO: Implement the nested class OrderedListNode (5 points).  This nested class
	// should be similar to the nested class ListNode of the class LinkedList, but
	// should store a data item of type Comparable rather than Object.
		
	  private static class OrderedListNode 
	  {
			//Data type Comparable instead of T
			Comparable theItem;

			//Same as ListNode in LinkedList
         OrderedListNode previous;
			OrderedListNode next;
         
         //Constructor for node 
			public OrderedListNode(Comparable object, OrderedListNode previous, OrderedListNode next) 
			{
				this.theItem = object;
				this.previous = previous;
				this.next = next;
			}
         
        //Returns item for comparison
        Comparable theItem() 
        {
            return theItem;
        }

        //Returns next node 
        OrderedListNode next() 
        {
            return next;
        }
        
        //Returns previous node 
        OrderedListNode previous() 
        {
            return previous;
        }
	  }
     
     public static void main(String[] args)
     {
        OrderedLinkedList list = new OrderedLinkedList();
        
        list.add("ryan");
        list.add("ben");
        
        list.remove("ryan");
        
        System.out.println(list);
     }
}