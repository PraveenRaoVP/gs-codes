package extrapractice;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class LinkedListImpl {
    Node head;

    public void addLast(int data) {
        if(head == null) {
            head = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        Node temp = head;

        while(temp.next!=null) {
            temp = temp.next;
        }
        temp.next = newNode;
        System.out.println("Added " + data + " to the end of the list");
    }

    public void addFirst(int data) {
        if(head == null) {
            head = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        System.out.println("Added" + data + " to the start of the list");
    }

    public void addMiddle(int data, int pos) {
        if(head == null) {
            head = new Node(data);
            return;
        }

        int counter = 0;
        Node temp = head;
        Node newNode = new Node(data);
        while(counter < pos && temp.next!=null) {
            temp = temp.next;
            counter++;
        }
        if(temp.next == null) {
            addLast(data);
        } else {
            newNode.next = temp.next;
            temp.next = newNode;
            System.out.println("Added " + data + " at index " + pos);
        }
    }

    public boolean contains(int data) {
        Node temp = head;
        while(temp.next!=null) {
            if(temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void removeFirst() {
        if(head == null) {
            return;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        System.out.println("Removed first element");
    }

    public void removeLast() {
        if(head == null) {
            return;
        }
        Node temp = head;
        while(temp.next.next!=null) {
            temp = temp.next;
        }
        temp.next = null;
        System.out.println("Removed last element");
    }

    public void removeFromMiddle(int data) {
        if(head == null) {
            return;
        }
        if(head.data == data) {
            removeFirst();
            return;
        }
        Node temp = head;
        Node temp2 = head.next;
        while(temp2!=null) {
            if(temp2.data == data) {
                temp.next = temp2.next;
                temp2.next = null;
                System.out.println("Removed " + data + " from the list");
                return;
            }
            temp = temp2;
            temp2 = temp2.next;
        }
        System.out.println("Element not found");
    }

    public void displayList() {
        Node temp = head;
        while(temp!=null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();   
    }

    public static void main(String[] args) {
        LinkedListImpl ll = new LinkedListImpl();
        ll.addFirst(1);
        ll.addLast(2);
        ll.addFirst(0);
        ll.addLast(3);
        ll.addMiddle(1, 1);
        ll.displayList();
        ll.removeFirst();
        ll.displayList();
        ll.removeLast();
        ll.displayList();
        ll.removeFromMiddle(2);
        ll.displayList();
    }
}
