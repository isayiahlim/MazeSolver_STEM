/**
 * Isayiah Lim
 * 2/7/2023
 * Data Structures Period 1
 * Mrs. Kankleborg
 * A last-in-first-out (LIFO) stack of generic items.
 *
 * @param <T> the type of item to store in the stack
 */
public class Stack<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        public Node()
        {
            data = null;
            next = null;
        }
        public Node(T data)
        {
            this.data = data;
            next = null;
        }
    }
    private Node<T> head;
    private int size;
    public Stack() {
        head = new Node<T>();
        size = 0;
    }
    
    public void push(T newItem) {
        if(newItem == null)
            throw new IllegalArgumentException("Invalid item given.");
        Node<T> temp = new Node<T>(newItem);
        temp.next = head;
        head = temp;
        size++;
    }
    
    public T pop() {
        if(size == 0)
            throw new IllegalStateException("Stack is empty.");
        T value = head.data;
        head = head.next;
        size--;
        return value;
    }
    
    public T peek() {
        if(size == 0)
            throw new IllegalStateException("Stack is empty.");
        return head.data;
    }
    
    public boolean isEmpty() {
        return !(size != 0);
    }
    
    public int size() {
        return size;
    }
}