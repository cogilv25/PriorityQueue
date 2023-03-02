/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

/**
 *
 * @author Calum Lindsay
 * @param <T> The type of things being stored.
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
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
    
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if(tailIndex >= storage.length - 1)
            throw new QueueOverflowException();
        
        tailIndex++;
        storage[tailIndex] = new PriorityItem<>(item,priority);
    }
    
    private int findIndexOfHighestPriorityItem() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /* Some less than pretty casting going on here but at the end of the day
            it's simply a loop finding the index of the item with the highest priority */
        int headIndex = tailIndex;
        for(int i=tailIndex;i>=0;--i)
        {
            //Find the 2 priorities to compare
            int currentHeadPriority = ((PriorityItem<T>)storage[headIndex]).getPriority();
            int nextItemPriority = ((PriorityItem<T>)storage[i]).getPriority();
            
            //Compare them and assign headIndex the index of the highest priority
            headIndex = (currentHeadPriority > nextItemPriority) ? headIndex : i;
        }
        
        return headIndex;
    }

    @Override
    public T head() throws QueueUnderflowException {
        int headIndex = findIndexOfHighestPriorityItem();
        return ((PriorityItem<T>)storage[headIndex]).getItem();
    }

    @Override
    public void remove() throws QueueUnderflowException {
        int headIndex = findIndexOfHighestPriorityItem();
        storage[headIndex] = storage[tailIndex];
        tailIndex--;
    }

    @Override
    public boolean isEmpty() {
        return (tailIndex < 0);
    }
    
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
