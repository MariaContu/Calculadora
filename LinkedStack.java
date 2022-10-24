import java.util.EmptyStackException;

public class LinkedStack {
    // Atributos
     private int count;
     private Node top;
     private int countMax;

    // construtor

    public LinkedStack() {
        count=0;
        top=null;
        countMax=0;
    }


    // insere o elemento e no topo da pilha
    public void push(int element)   {
        Node elem = new Node(element);
        count++;
        if (count>countMax)countMax=count;
        top=elem;
    }

    // remove e retorna o elemento do topo da pilha
    // (erro se a pilha estiver vazia)
    public int pop() throws EmptyStackException {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        int element = top.getElement();
        top = top.getNext();
        count--;
        return element;
    }

    // retorna, mas não remove, o elemento do topo da
    // pilha (erro se a pilha estiver vazia)
    public int top()    {
        if (isEmpty())  {
            throw new EmptyStackException();
        }
        return top.getElement();
    }

    // retorna o número de elementos da pilha
    public int getCount()   {
        return count;
    }

    // retorna true se a pilha estiver vazia, e ]
    // false caso contrário
    public boolean isEmpty()    {
        return count == 0;
    }

    public int getCountMax() {
        return countMax;
    }
}

