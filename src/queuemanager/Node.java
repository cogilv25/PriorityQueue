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
 * @author Calum
 */
public class Node<T> {
    
    private T value;
    
    private Node<T> next = null;
    
    public Node(T value)
    {
        this.value = value;
    }
    
    public T getValue()
    {
        return value;
    }
    
    public void setNext(Node<T> next)
    {
        this.next = next;
    }
    
    public Node<T> getNext()
    {
        return next;
    }
}
