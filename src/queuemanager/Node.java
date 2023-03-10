/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queuemanager;

/**
 * Implementation of a node used by all linked lists in the project. 
 * 
 * @param <T> The type of thing being stored.
 * 
 * @author Calum Lindsay
 */
public class Node<T> {
    /**
     * The value stored in the node.
     */
    private T value;
    
    /**
     * The node pointed to by this node.
     */
    private Node<T> next = null;
    
    /**
     * Create a new node storing the provided value.
     * 
     * @param value The value to be stored in the node.
     */
    public Node(T value)
    {
        this.value = value;
    }
    
    /**
     * Get the value stored by the node.
     * 
     * @return the value stored by the node.
     */
    public T getValue()
    {
        return value;
    }
    
    /**
     * Set the node pointed to by this node.
     * 
     * @param next A node for this node to point to.
     */
    public void setNext(Node<T> next)
    {
        this.next = next;
    }
    
    /**
     * Get the node pointed to by this node.
     * 
     * @return The node this node points to.
     */
    public Node<T> getNext()
    {
        return next;
    }
}
