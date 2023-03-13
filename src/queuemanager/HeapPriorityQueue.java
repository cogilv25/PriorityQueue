package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a heap for storage.
 *
 * Because Java does not allow generic arrays, this is implemented as an
 * array of Object and individual items are cast to their type when returned.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T head() throws QueueUnderflowException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() throws QueueUnderflowException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
