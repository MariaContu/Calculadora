public class Node {
    public Node next;
    public int element;

    //constroi node

    public Node(int element) {
        next=null;
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        next=node;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
}
