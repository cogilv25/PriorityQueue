package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a binary heap for storage.
 *
 * Because Java does not allow generic arrays, this is implemented as an
 * array of Object and individual items are cast to their type when returned.
 * 
 * It would be nice to be able to use pointer maths in some of these methods but
 * this is Java so we will just have to make do.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {

    
    private final Object [] storage;
    
    private final int capacity;
    
    private int size = 0;
    
    
    public HeapPriorityQueue(int capacity)
    {
        this.capacity = capacity;
        storage = new Object[capacity];
    }
    
    
    private void bubbleUp()
    {
        /* Initilalize indexes */
        int childIndex = size-1;
        int parentIndex = size/2-1;
        
        while(parentIndex >= 0)
        {
            /* Get Priorities */
            int parentPriority = ((PriorityItem<T>)storage[parentIndex]).getPriority();
            int childPriority = ((PriorityItem<T>)storage[childIndex]).getPriority();
            
            /* If parent has a higher priority then we're done */
            if(parentPriority >= childPriority)
                break;
            else
            {
                /* Otherwise we swap parent and child */
                Object temp = storage[childIndex];
                storage[childIndex] = storage[parentIndex];
                storage[parentIndex] = temp;
            }
            
            /* Re-calculate indexes for next loop */
            childIndex = parentIndex;
            parentIndex = (childIndex+1)/2-1;
        }
    }
    
    private void bubbleDown()
    {
        /* Initilalize variables for sorting */
        int index = 0;
        int childIndex = 1;
        
        while(childIndex<size)
        {
            /* Get the index of the child with the highest priority */
            int childPriority = ((PriorityItem<T>)storage[childIndex]).getPriority();
            if(childIndex + 1 <size)
            {
                int rightChildPriority = ((PriorityItem<T>)storage[childIndex+1]).getPriority();;
                if(childPriority<rightChildPriority)
                {
                    childPriority = rightChildPriority;
                    childIndex++;
                }
            }
            
            int parentPriority = ((PriorityItem<T>)storage[index]).getPriority();            
            if(parentPriority < childPriority)
            {
                /* Swap parent and child then update index */
                Object temp = storage[index];
                storage[index] = storage[childIndex];
                storage[childIndex] = temp;
                index = childIndex;
            }
            else
                break;
            
            /* Re-calculate the index of the left child */
            childIndex = index * 2 + 1;
        }
    }
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if(size == capacity)
            throw new QueueOverflowException();
        
        storage[size] = new PriorityItem<>(item,priority);
        size++;
        
        bubbleUp();
    }

    @Override
    public T head() throws QueueUnderflowException {
        if(size == 0)
            throw new QueueUnderflowException();
        
        return ((PriorityItem<T>)storage[0]).getItem();
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if(size == 0)
            throw new QueueUnderflowException();
        size--;
            
        if(size > 0)
        {
            storage[0] = storage[size];
            bubbleDown();
        }  
    }
    
    @Override
    public String toString() {
        /* Construct a comma delimited list of items in the queue. */
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }

    @Override
    public boolean isEmpty() {
        return (size < 1);
    }
 
}
