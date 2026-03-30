package lib;

public class Queue<Generic> {
    LinkedList<Generic> list = new LinkedList<Generic>();
    public Queue(){}

    public void push(Generic element){
        list.push(element);
    }

    public Generic pop() throws Exception {
        Generic e = this.peak();
        list.removeFirst();
        return e;
    }

    public Generic peak() throws Exception {
        return list.get(0);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int length(){
        return list.length();
    }

    @Override
    public String toString() {
        return list.toString();
    } 

}