/*
* Written by Umut Yildirim
*/
public class GenericQueue<T> {
    private Object[] array;
    private int front;
    private int rear;
    private int size;

    public GenericQueue() {
        array = new Object[10];  // Initial size of 10
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(T item) {
        if (size == array.length) {
            resize();
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;  // Empty queue
        }
        T item = (T) array[front];
        front = (front + 1) % array.length;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        Object[] newArray = new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        front = 0;
        rear = size;
        array = newArray;
    }
}
