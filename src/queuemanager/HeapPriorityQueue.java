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
public class HeapPriorityQueue<T> implements PriorityQueue<T>
{

    /**
     * Where the data is actually stored.
     */
    private final Object [] storage;
    
    /**
     * The maximum number of items the Queue can store.
     */
    private final int capacity;
    
    /**
     * The current number of items stored.
     */
    private int size = 0;
    
    
    /**
     * Create a new empty queue with the given capacity.
     *
     * @param capacity
     */
    public HeapPriorityQueue(int capacity)
    {
        this.capacity = capacity;
        storage = new Object[capacity];
    }
    
    
    /**
     * Helper method to ensure the integrity of the binary heap is maintained
     * after an item is added to the queue.
     */
    private void bubbleUp()
    {
        /* Initilalize indexes. */
        int index = size-1;
        int parentIndex = size/2-1;
        
        while(parentIndex >= 0)
        {
            /* Get priorities. */
            int parentPriority = ((PriorityItem<T>)storage[parentIndex]).getPriority();
            int childPriority = ((PriorityItem<T>)storage[index]).getPriority();
            
            /* If parent has a higher priority then we're done. */
            if(parentPriority >= childPriority)
                break;
            else
            {
                /* Otherwise we swap parent and child. */
                Object temp = storage[index];
                storage[index] = storage[parentIndex];
                storage[parentIndex] = temp;
            }
            
            /* Re-calculate indexes for next loop. */
            index = parentIndex;
            parentIndex = (index+1)/2-1;
        }
    }
    
    
    /**
     * Helper method to ensure the integrity of the binary heap is maintained
     * after an item is removed from the queue.
     */
    private void bubbleDown()
    {
        /* Initilalize indexes. */
        int index = 0;
        int childIndex = 1;
        
        while(childIndex < size)
        {
            /* Get the index and priority of the highest priority child. */
            int childPriority = ((PriorityItem<T>)storage[childIndex]).getPriority();
            if(childIndex + 1 < size)
            {
                int rightChildPriority = ((PriorityItem<T>)storage[childIndex+1]).getPriority();
                if(childPriority<rightChildPriority)
                {
                    childPriority = rightChildPriority;
                    ++childIndex;
                }
            }
            
            /* Get parent priority and compare to child priority. If the parent
             * has a higher property we can break out of the method. */
            int parentPriority = ((PriorityItem<T>)storage[index]).getPriority();            
            if(parentPriority > childPriority)
                break;
            else
            {
                /* Otherwise we will swap parent and child then update index. */
                Object temp = storage[index];
                storage[index] = storage[childIndex];
                storage[childIndex] = temp;
                index = childIndex;
            }
            
            /* Re-calculate the index of the next left child */
            childIndex = index * 2 + 1;
        }
    }
    
    
    /**
     * Helper method for toString to assess if an index is the first item on a
     * new level within the binary heap.
     * 
     * @param index The index to be assessed.
     * @return 
     */
    private boolean isStartOfHeapLevel(int index)
    {
        for(int i = 2; i <= index + 1; i *= 2)
            if(i - 1 == index)
                return true;
        return false;
    }
    
    
    /* These methods inherit their JavaDoc comments from PriorityQueue. */
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException
    {
        if(size == capacity)
            throw new QueueOverflowException();
        
        /* Create the new item at the end of the heap */
        storage[size] = new PriorityItem<>(item,priority);
        ++size;
        
        /* Resolve integrity of the heap. */
        bubbleUp();
    }

    
    @Override
    public T head() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        return ((PriorityItem<T>)storage[0]).getItem();
    }

    
    @Override
    public void remove() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /* If no exception was thrown there is at least one item to remove. */
        size--;
        /* If there was only one item size == 0 and we are done. */
        if(size > 0)
        {
            /* Replace head with the last item in the heap. */
            storage[0] = storage[size];
            /* Resolve integrity of the binary heap. */
            bubbleDown();
        }  
    }
    
    
    @Override
    public String toString()
    {
        /* Construct a comma delimited list of items in the queue. Displaying
         * each level of the heap on a new line. */
        String result = "[";
        for (int i = 0; i < size; ++i) {
            
            if (i > 0)
                result += ", ";
            
            if(isStartOfHeapLevel(i))
                result += '\n';
            
            result += storage[i];
        }
        result = result + "]";
        return result;
    }

    
    @Override
    public boolean isEmpty()
    {
        return (size < 1);
    }
 
}
