package queuemanager;

/**
 * Implementation of a node used by all linked lists in the project. 
 * 
 * @param <T> The type of thing being stored.
 * 
 * @author Calum Lindsay
 */
public class Node<T>
{
    
    /**
     * The value stored by the node.
     */
    private final T value;
    
    /**
     * The node pointed to by this node.
     */
    private Node<T> next = null;
    
    
    /**
     * Creates a new node.
     * 
     * @param value The value to be stored in the node.
     */
    public Node(T value)
    {
        this.value = value;
    }
    
    
    /**
     * The value stored by the node.
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
     * The node pointed to by this node.
     * 
     * @return The node this node points to.
     */
    public Node<T> getNext()
    {
        return next;
    }
}
