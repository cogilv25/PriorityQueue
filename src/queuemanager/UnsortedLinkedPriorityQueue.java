/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted linked list for
 * storage.
 * 
 * @param <T> The type of things being stored.
 *
 * @author Calum Lindsay
 */
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * The head or start of the linked list.
     */
    private Node<PriorityItem<T>> head;
    
    /**
     * The tail or end of the linked list.
     * Keeping track of this makes insertions much faster as we don't need to
     * traverse the whole list to add an item at the end.
     */
    private Node<PriorityItem<T>> tail;

    /**
     * The number of items held in the linked list.
     */
    private int count;
    
    /**
     * Initializes the ADT.
     */
    public UnsortedLinkedPriorityQueue()
    {
        head = null;
        count = 0;
    }
    
    /**
     * Helper function to find the node before the node of the highest priority
     * item in the queue.
     * 
     * @return  index of the highest priority item stored
     * @throws QueueUnderflowException 
     */
    private Node<PriorityItem<T>> findNodeBeforeHighestPriorityNode() throws QueueUnderflowException
    {
        if(isEmpty())
            throw new QueueUnderflowException();
        
        /**
         * If there is only 1 node in the linked list then that node is the
         * highest priority node and the node behind that node doesn't exist.
         */
        if(count == 1)
            return null;
        
        /**
         * The node before the highest priority node so far.
         */
        Node<PriorityItem<T>> nBHPNode = null;
        
        /**
         * The highest priority node so far.
         */
        Node<PriorityItem<T>> hPNode = head;
        
        /**
         * The node before the node being tested against the highest priority 
         * node do far.
         */
        Node<PriorityItem<T>> prevNode = head;
        
        /**
         * Some less than pretty casting going on here and we have to keep track
         * of more nodes than I would like but at the end of the day it's simply
         * a loop finding the node before the node with the highest priority.
        */
        for(int i=0;i<count-1;++i)
        {
            /**
             * Find the 2 priorities to compare.
             */
            int highestPriorityNodePriority = ((PriorityItem<T>)hPNode.getValue()).getPriority();
            int currentNodePriority = ((PriorityItem<T>)prevNode.getNext().getValue()).getPriority();
            
            /**
             * Compare them and assign hPNode to the node with the highest
             * priority and nBHPNode to the node behind it.
             */
            if (highestPriorityNodePriority < currentNodePriority)
            {
                nBHPNode = prevNode;
                hPNode = prevNode.getNext();
            }
            
            /**
             * Move to the next node in the linked list.
             */
            prevNode = prevNode.getNext();
        }
        
        return nBHPNode;
    }
    /**
     * Takes an item and a priority, creates a Node containing a PriorityItem
     * and inserts it at the end of the linked list.
     * 
     * @param item
     * @param priority
     * @throws QueueOverflowException 
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        Node<PriorityItem<T>> prevItem = head;
        if(count>1)
            prevItem = tail;
        
        tail = new Node<>(new PriorityItem<>(item,priority));
        
        if(count<1)
            head = tail;
        else
            prevItem.setNext(tail);
        
        count++;
        
    }

    /**
     * Finds the highest priority item in the queue and returns it.
     * 
     * @return The highest priority item in the queue.
     * @throws QueueUnderflowException 
     */
    @Override
    public T head() throws QueueUnderflowException {
        Node<PriorityItem<T>> nBHPNode = findNodeBeforeHighestPriorityNode();
        if(nBHPNode == null)
            return head.getValue().getItem();
        return nBHPNode.getNext().getValue().getItem();
    }

    
    /**
     * Finds the highest priority item in the queue and removes it.
     * 
     * @throws QueueUnderflowException 
     */
    @Override
    public void remove() throws QueueUnderflowException {
        Node<PriorityItem<T>> prevNode = findNodeBeforeHighestPriorityNode();
        Node<PriorityItem<T>> nextNode;
        if(count == 1)
        {
            head = null;
            tail = null;
            count--;
            return;
        }
        if(prevNode == null)
        {
            head = head.getNext();
            count--;
            return;
        }
        nextNode = prevNode.getNext().getNext();
        
        if(nextNode == null)
            tail = prevNode;
        
        prevNode.setNext(nextNode);
        
        count--;
    }

    /**
     * Returns true if the queue is empty and false otherwise.
     * 
     * @return A boolean indicating if the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return (count < 1);
    }
    
    /**
     * Creates and returns a string representing the state of the queue
     * 
     * @return A string representing the state of the queue
     */
    @Override
    public String toString() {
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
    
}
