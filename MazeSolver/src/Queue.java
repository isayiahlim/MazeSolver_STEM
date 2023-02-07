/**
 * Isayiah Lim
 * 2/7/2023
 * Data Structures Period 1
 * Mrs. Kankleborg
 * A first-in-first-out (FIFO) queue of generic items.
 *
 * @param <T> the type of item to store in the queue
 */
public class Queue<T> {
    private static class Node<T> {
        Node<T> next;
        T data;
        public Node()
        {
            next = null;
            data = null;
        }
        public Node(T data)
        {
        	this.data = data;
        	next = null;
        }
    }
    
    // ADD YOUR CODE HERE.
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public Queue() {
        head = null;
        tail = null;
    }
    
    public void enqueue(T newItem) {
        if(newItem == null) throw new IllegalArgumentException("");
    	if(size == 0)
        {
            head = new Node<T>(newItem);
            tail = head;
        }
        else if (size == 1)
        {
        	tail = new Node<T>(newItem);
        	head.next = tail;
        }
        else
        {
            tail.next = new Node<T>(newItem);
            tail = tail.next;
        }
        size ++;
    }
    
    public T dequeue() {
        if(isEmpty()) throw new IllegalStateException("");
        T temp = head.data;
        if(size == 1)
        {
            head = null;
            tail = null;
        }
        else
        {
            head = head.next;
        }
        size--;
        return temp;
    }
    
    public T peek() {
        if(isEmpty()) throw new IllegalStateException("");
        return head.data;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }
}