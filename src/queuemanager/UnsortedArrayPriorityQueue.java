package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted array for storage.
 *
 * Because Java does not allow generic arrays, this is implemented as an
 * array of Object and individual items are cast to their type when returned.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T>
{

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The index of the last item stored.
     */
    private int tailIndex;
    
    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public UnsortedArrayPriorityQueue(int size)
    {
        tailIndex = -1;
        storage = new Object[size];
    }
    
    /**
     * Helper function to find the index of the highest priority item in the 
     * queue.
     * 
     * @return  index of the highest priority item stored
     * @throws QueueUnderflowException 
     */
    private int findIndexOfHighestPriorityItem() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /* Some less than pretty casting going on here but it's simply a loop
         * finding the index of the item with the highest priority. */
        int headIndex = tailIndex;
        for(int i=tailIndex;i>=0;--i)
        {
            /* Find the 2 priorities to compare. */
            int currentHeadPriority = ((PriorityItem<T>)storage[headIndex]).getPriority();
            int nextItemPriority = ((PriorityItem<T>)storage[i]).getPriority();
            
            /* Compare them and assign headIndex to the index of the highest 
             * priority. */
            headIndex = (currentHeadPriority > nextItemPriority) ? headIndex : i;
        }
        
        return headIndex;
    }
    
    
    /* These functions inherit their JavaDoc comments from PriorityQueue. */
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException
    {
    /* Takes an item and a priority, creates a PriorityItem and adds this
     * to the end of the internal Object array. */
        if(tailIndex >= storage.length - 1)
            throw new QueueOverflowException();
        
        tailIndex++;
        storage[tailIndex] = new PriorityItem<>(item,priority);
    }
    
    
    @Override
    public T head() throws QueueUnderflowException
    {
        int headIndex = findIndexOfHighestPriorityItem();
        return ((PriorityItem<T>)storage[headIndex]).getItem();
    }

    
    @Override
    public void remove() throws QueueUnderflowException 
    {
        /* Overwrite the highest priority item in the queue with the last
         * item in the queue effectively removing it. */
        int headIndex = findIndexOfHighestPriorityItem();
        storage[headIndex] = storage[tailIndex];
        
        /* Decrement the tailIndex. */
        tailIndex--;
    }
    
    
    @Override
    public String toString() {
        /* Construct a comma delimited list of items in the queue. */
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
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
        return (tailIndex < 0);
    }
    
}
