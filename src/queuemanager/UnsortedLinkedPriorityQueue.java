package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted linked list for
 * storage.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T>
{

    /**
     * The head or start of the linked list.
     */
    private Node<PriorityItem<T>> head = null;
    
    /**
     * The tail or end of the linked list.
     * Keeping track of this makes insertions much faster as we don't need to
     * traverse the whole list to add an item at the end.
     */
    private Node<PriorityItem<T>> tail = null;

    /**
     * The number of items held in the queue.
     */
    private int count = 0;
    
    /**
     * Helper function to find the node preceding the highest priority node.
     * 
     * @return  Node preceding the highest priority node or null where the highest priority node is head.
     * @throws QueueUnderflowException 
     */
    private Node<PriorityItem<T>> findNodeBeforeHighestPriorityNode() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /* If there is only 1 node in the linked list then head is the
         * highest priority node and the node behind head doesn't exist. */
        if(count == 1)
            return null;
        
        /* The node before the highest priority node. */
        Node<PriorityItem<T>> nBHPNode = null;
        
        /* The highest priority node. */
        Node<PriorityItem<T>> hPNode = head;
        
        /* The node before the node being tested. */
        Node<PriorityItem<T>> prevNode = head;
        
        /* Find the node before the node with the highest priority. */
        for(int i=0;i<count-1;++i)
        {
            /* Find the 2 priorities to compare. */
            int highestPriorityNodePriority = ((PriorityItem<T>)hPNode.getValue()).getPriority();
            int currentNodePriority = ((PriorityItem<T>)prevNode.getNext().getValue()).getPriority();
            
            /* Compare them and assign hPNode to the node with the highest
             * priority and nBHPNode to the node behind it. */
            if (highestPriorityNodePriority < currentNodePriority)
            {
                nBHPNode = prevNode;
                hPNode = prevNode.getNext();
            }
            
            /* Move to the next node in the linked list. */
            prevNode = prevNode.getNext();
        }
        
        return nBHPNode;
    }
    
    
    /* These functions inherit their JavaDoc comments from PriorityQueue. */
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException
    {
        /* Remember the tail */
        Node<PriorityItem<T>> prevTail = tail;
        
        /* Create a new Node containing a new PriorityItem and insert it at the
         * end of the linked list. */
        tail = new Node<>(new PriorityItem<>(item,priority));
        
        /* Link the previous tail to the new node when appropriate. */
        if(count<1)
            head = tail;
        else
            prevTail.setNext(tail);
        
        count++;
        
    }

    
    @Override
    public T head() throws QueueUnderflowException
    {
        Node<PriorityItem<T>> prevNode = findNodeBeforeHighestPriorityNode();
        
        /* return the item at head if prevNode is null otherwise
         * return the item at the node after prevNode. */
        return (prevNode == null) ?
            head.getValue().getItem() : 
            prevNode.getNext().getValue().getItem();
    }

    
    @Override
    public void remove() throws QueueUnderflowException
    {
        Node<PriorityItem<T>> prevNode = findNodeBeforeHighestPriorityNode();
        
        /* Since there was no exception thrown we know there is at least one
         * item in the list. */
        count--;
        
        /* If there was only one item in the list we're done */
        if(count == 0)
            return;
        
        /* If we are removing head we can just set head to it's next node. */
        if(prevNode == null)
        {
            head = head.getNext();
            return;
        }
        
        Node<PriorityItem<T>> nextNode = prevNode.getNext().getNext();
        
        /* If removing tail we can just set tail to the node before it.
         * otherwise we can link the node before the one being removed to the
         * node after the one being removed. */
        if(nextNode == null)
            tail = prevNode;
        else
            prevNode.setNext(nextNode);
    }
    
    
    @Override
    public String toString() {
        /* Construct a comma delimited list of items in the queue. */
        String result = "[";
        Node<PriorityItem<T>> current = head;
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + current.getValue();
            current = current.getNext();
        }
        result = result + "]";
        return result;
    }
    
    
    @Override
    public boolean isEmpty() {
        return (count < 1);
    }
    
}
