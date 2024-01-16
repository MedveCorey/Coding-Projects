package linkedlists;

public class LinkedList {

    Node head;
    int length = 0;

    public void append(int newValue) {
        Node newNode = new Node(newValue);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = newNode;
            length++;
        }
        
    }

    public void display() {
        if (head == null) {
            System.out.println("Empty List");
        } else {
            Node currentNode = head;
            while (currentNode.nextNode != null) {
                System.out.println("Value: " + currentNode.data);
                currentNode = currentNode.nextNode;
            }
            System.out.println("Value: " + currentNode.data);
        }
        
    }

    public void insertAt(int location, int value) {
        Node newNode = new Node(value);
        if (head != null) {
            int counter = 0;
            Node currentNode = head;
            while (currentNode.nextNode != null) {
                if(counter == location){
                    newNode.nextNode = currentNode.nextNode;
                    break;
                }
                currentNode = currentNode.nextNode;
                counter++;
            }
        }

    }
    
    public void delete(int location){
        if (head != null) {
            int counter = 0;
            Node currentNode = head;
            while (currentNode.nextNode != null) {
                if(counter == location){
                    currentNode.nextNode = currentNode.nextNode.nextNode;
                    break;
                }
            }
        }
    }
}
