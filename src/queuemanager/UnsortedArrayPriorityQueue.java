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

    public UnsortedArrayPriorityQueue(int capacity)
    {
        
    }
    
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T head() throws QueueUnderflowException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove() throws QueueUnderflowException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
