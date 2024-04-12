package assignments.assignment13_arraylist;

import java.util.ArrayList;

/* Create a class implementing a circular buffer using an ArrayList of Strings for a messaging service. This buffer should have a fixed size and overwrite the oldest element when full. */

public class Problem3 {
    private ArrayList<String> buffer;
    private int size;
    private int currentIndex;
    
    public Problem3(int size) {
        this.size = size;
        buffer = new ArrayList<>(size);
        currentIndex = 0;
    }

    public void addMessage(String message) {
        if (buffer.size() < size) {
            buffer.add(message);
        } else {
            buffer.set(currentIndex, message);
            currentIndex = (currentIndex + 1) % size;
        }
    }

    public void printMessages() {
        for (String message : buffer) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        Problem3 buffer = new Problem3(3);
        buffer.addMessage("Message1");
        buffer.addMessage("Message2");
        buffer.addMessage("Message3");
        buffer.printMessages();
        buffer.addMessage("Message4");
        buffer.printMessages();
        buffer.addMessage("Message5");
        buffer.addMessage("Message6");
        buffer.printMessages();
    }
}
