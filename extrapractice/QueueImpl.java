package extrapractice;

import java.util.Queue;

public class QueueImpl {
    private int[] queue;
    private int front, rear;

    public QueueImpl(int size) {
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    private void shift() {
        int index = 0;
        int n = queue.length;
        for (int i = 0; i < n; i++) {
            if (queue[i] != Integer.MIN_VALUE && queue[i]!=0) {
                int temp = queue[index];
                queue[index] = queue[i];
                queue[i] = temp;
                
                index++;
            }
        }
        front = 0;
        rear = 0;
        while(queue[rear] != 0 && queue[rear] != Integer.MIN_VALUE) {
            rear++;
        }
    }

    public void offer(int data) {
        if(rear >= queue.length) {
            System.out.println("Queue is full.");
            return;
        }
        if(front==-1) {
            front++;
        }
        queue[++rear] = data;
        shift();
        System.out.println("Data " + data + " added to the queue");
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int poll() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            front = -1;
            rear = -1;
            return -1;
        }
        int el = queue[front];
        queue[front] = Integer.MIN_VALUE;
        shift();
        return el;
    }

    public int peek() {
        if(isEmpty()) {
            return -1;
        }
        return queue[front];
    }

    public void displayElements() {
        for(int i: queue) {
            // if(i!=Integer.MIN_VALUE)
               System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueImpl q = new QueueImpl(5);
        q.offer(1);
        q.offer(2);
        q.displayElements();
        q.poll();
        q.displayElements();
        q.poll();
        q.displayElements();
        q.poll();
        q.displayElements();
        q.offer(1);
        q.offer(2);
        q.displayElements();

    }
}
