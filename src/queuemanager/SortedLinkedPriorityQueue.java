package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a sorted linked list for
 * storage.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T>
{
    
    /**
     * The head or start of the linked list.
     */
    private Node<PriorityItem<T>> head = null;

    /**
     * The number of items held in the queue.
     */
    private int count = 0;
    
    
    /* These methods inherit their JavaDoc comments from PriorityQueue. */
    
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException
    {
        /* Create a new Node containing a new PriorityItem */
        Node<PriorityItem<T>> newNode = new Node<>(new PriorityItem<>(item,priority));
        
        /* If the queue is empty insert at head and return. */
        if(isEmpty())
        {
            head = newNode;
            ++count;
            return;
        }
        
        /* Variables to track insertion point */
        Node<PriorityItem<T>> insertAfter = null;
        Node<PriorityItem<T>> insertBefore = head;
        
        /* Find the insertion point. */
        while(insertBefore != null)
        {
            if(priority > insertBefore.getValue().getPriority())
                break;

            insertAfter = insertBefore;
            insertBefore = insertBefore.getNext();
        }
        
        
        /* Insert it into the linked list at the insertion point and update head
         * if we replaced it. */
        newNode.setNext(insertBefore);
        if(insertAfter == null)
            head = newNode;
        else
            insertAfter.setNext(newNode);
        
        ++count;
    }

    
    @Override
    public T head() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        return head.getValue().getItem();
    }

    
    @Override
    public void remove() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /* We know there is at least one item in the list. */
        count--;
        
        /* Move the head reference forward one place in the list. */
        if(count > 0)
            head = head.getNext();
    }
    
    
    @Override
    public String toString() {
        /* Construct a comma delimited list of items in the queue. */
        String result = "[";
        Node<PriorityItem<T>> current = head;
        for (int i = 0; i < count; ++i) {
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
