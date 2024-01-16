import java.io.*;
import java.util.*;

public class Tower<T> {
    private Disk<T> base; // pointer to first disk at BASE of the tower (i.e. the old head pointer)
    private Disk<T> top; // pointer to last disk at TOP of the tower (i.e. the old tail pointer)

    public Tower() {
        base = null; // compiler does this anyway. just for emphasis
    }

    public boolean empty() {
        return (base == null);
    }

    // i.e. the old insertAtTail or now insertAtTop we call it a PUSH
    public void push(T label) {
        if (base == null) {
            base = new Disk<T>(label);
            return;
        }
        Disk<T> cur = base;

        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Disk<T>(label);
    }

    // i.e. the old removeAtTail or now removeAtTop we call it a POP
    public Disk<T> pop() throws Exception
	{
		Disk<T> cur = base;
        Disk<T> previous = null;
        try{
            if(base == null)
            {
                return null;
            }
            while(cur.next !=null)
            {
                previous = cur;
                cur = cur.next;
            }
            if(cur.next == base.next)
            {
                base = base.next;
            }
            previous.next = null;
            return null;
        }catch(Exception e){
            return null;
        }
	}

    // prints the tower base to top, left to right, respectively //
    public String toString() {
        if (base == null)
            return "EMPTY\t";
        String toString = "";
        for (Disk<T> curr = base; curr != null; curr = curr.next)
            toString += curr.label + " ";

        return toString;
    }
} // END TOWER CLASS

/*
 * #############################################################################
 * ##
 */

class Disk<T> {
    T label;
    Disk<T> next;

    Disk(T data) {
        this(data, null);
    }

    Disk(T label, Disk<T> next) {
        this.label = label;
        this.next = next;
    }

} // END DISK CLASS
