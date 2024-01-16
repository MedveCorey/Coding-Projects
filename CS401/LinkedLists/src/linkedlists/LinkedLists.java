package linkedlists;

public class LinkedLists {

    public static void main(String[] args) {
        new LinkedLists();
    }
    public LinkedLists(){
        LinkedList myList = new LinkedList();
        myList.append(5);
        myList.append(3);
        myList.append(7);
        System.out.println(myList.length);
        myList.insertAt(1, 10);
        myList.display();
        System.out.println();
        myList.delete(2);
        myList.display();
        
    }
}
