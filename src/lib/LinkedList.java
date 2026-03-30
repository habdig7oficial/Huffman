package lib;

public class LinkedList<Generic> {
    private LinkedNode<Generic> root;
    private int size;
    public LinkedList(){}

    public void push(Generic element) {
        LinkedNode<Generic> node = new LinkedNode<Generic>(element);
        if (size == 0) {
            root = node;
            root.setBoth(node);
            size++;
            return;
        }
        node.setPrevNext(root.getPrevious(), root);
        root.getPrevious().setNext(node);
        root.setPrevious(node);
        size++;
        return;
    }
    public Generic get(int index) throws Exception {
        LinkedNode<Generic> node = root;
        if (index >= size) 
            throw new Exception("Invalid index access");

        for (int i = 0; i < index; i++) 
            node = node.getNext();
        
        return node.getElement();
    }

    public void removeLast() throws Exception {
        switch (size) {
            case 0:
                throw new Exception("Stack Underflow; Stack Empty");
            case 1:
                root = null;

                break;

            default:
                LinkedNode<Generic> aux = root.getPrevious().getPrevious();
                aux.setNext(root);
                //aux.setNext(null);
                root.setPrevious(aux);
                //System.out.println(root);
                break;
        }
        size--;
    }

    public void removeFirst() throws Exception {
        switch (size) {
            case 0:
                throw new Exception("Stack Underflow; Stack Empty");
            case 1:
                root = null;

                break;

            default:
                LinkedNode<Generic> aux = root.getNext();
                aux.setPrevious(root.getPrevious());
                //aux.setNext(null);
                root.getPrevious().setNext(aux);
                root = aux;
                break;
        }
        size--;
    }


    public Generic getLast() throws Exception {
        if (size == 0) 
            throw new Exception("Stack is Empty");
        return root.getPrevious().getElement();
    }

    public Generic getFirst() throws Exception {
        if (size == 0) 
            throw new Exception("Stack is Empty");
        return root.getElement();
    }


    public int getSize() {
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int length(){
        return size;
    }

    @Override
    public String toString() {
        String str = "[";

        LinkedNode<Generic> node = root;
        for (int i = 0; i < size; i++) {
            str += node.getElement().toString() + ", ";
            node = node.getNext();
        }

        return str + "]";
    }
}