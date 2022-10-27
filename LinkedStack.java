

import java.util.ArrayList;

public class LinkedStack {

    public class Node {
        private String element;
        private Node next = null;
        Node(String element) {this.element = element;}
    }
        private Node head;
    private int count = 0;
    private int countMax = 0;

    public LinkedStack() {
        head = null;
    }

    public void push(String element) {
        Node n = new Node(element);
        n.next = head;
        head = n;
        count++;
        if(count > countMax) countMax = count;
        
    }

    public int getCountMax() {
        return countMax;
    }

    public int size() {
        return count;
    }

    public String pop() {
//        if (head==null) throw new EmptyStackException();
        Node aux = head;
        head = head.next;
        count--;
        return aux.element;
    }

    public String top() {
        return head.element;
    }

    public boolean isEmpty() {
        if (count == 0)
            return true;
        else
            return false;
    }

    public void clear() {
        head = null;
        count=0;
    }

    public String toString() {
        StringBuilder saida = new StringBuilder();
        LinkedStack reverseQueue = new LinkedStack();

        Node aux = head;
        while (aux != null) {
            reverseQueue.push(aux.element);
            aux = aux.next;
        }

        while (reverseQueue.count > 0)
            saida.append(reverseQueue.pop());

        return saida.toString();
    }
}
