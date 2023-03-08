package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted array for storage.
 *
 * Because Java does not allow generic arrays, this is implemented as an
 * array of Object and some nasty casting has to be done here and there.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {

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
     * Takes an item and a priority, creates a PriorityItem and adds this
     * to the end of the internal Object arrays used capacity. Throws an
     * exception if the capacity of the array is used up.
     * 
     * @param item The item to be added to the queue
     * @param priority The priority of the item to be added to the queue
     * @throws QueueOverflowException 
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if(tailIndex >= storage.length - 1)
            throw new QueueOverflowException();
        
        tailIndex++;
        storage[tailIndex] = new PriorityItem<>(item,priority);
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
        
        /**
         * Some less than pretty casting going on here but at the end of the day
         * it's simply a loop finding the index of the item with the highest priority
        */
        int headIndex = tailIndex;
        for(int i=tailIndex;i>=0;--i)
        {
            /**
             * Find the 2 priorities to compare.
             */
            int currentHeadPriority = ((PriorityItem<T>)storage[headIndex]).getPriority();
            int nextItemPriority = ((PriorityItem<T>)storage[i]).getPriority();
            
            /**
             * Compare them and assign headIndex the index of the highest 
             * priority.
             */
            headIndex = (currentHeadPriority > nextItemPriority) ? headIndex : i;
        }
        
        return headIndex;
    }
    
    /**
     * Returns the highest priority item in the queue.
     * 
     * @return The value of the highest priority item in the queue
     * @throws QueueUnderflowException 
     */
    @Override
    public T head() throws QueueUnderflowException {
        int headIndex = findIndexOfHighestPriorityItem();
        return ((PriorityItem<T>)storage[headIndex]).getItem();
    }

    /**
     * Removes the highest priority item from the queue.
     * 
     * @throws QueueUnderflowException 
     */
    @Override
    public void remove() throws QueueUnderflowException {
        int headIndex = findIndexOfHighestPriorityItem();
        storage[headIndex] = storage[tailIndex];
        tailIndex--;
    }

    /**
     * Returns true if the queue is empty, otherwise returns true.
     * 
     * @return Boolean value indicating if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (tailIndex < 0);
    }
    
    /**
     * Creates and returns a string representing the state of the queue
     * 
     * @return A string representing the state of the queue
     */
    @Override
    public String toString() {
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
    
}
