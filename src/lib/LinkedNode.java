package lib;

public class LinkedNode<Generic>{
    private Generic element;
    private LinkedNode<Generic> previous;
    private LinkedNode<Generic> next;

    public LinkedNode(Generic element){
        this.element = element;
        this.previous = this;
        this.next = this;
    }
    public LinkedNode(Generic element, LinkedNode<Generic> previous){
        this.element = element;
        this.previous = this;
    }
    public LinkedNode(Generic element, LinkedNode<Generic> previous, LinkedNode<Generic> next){
        this.element = element;
        this.previous = previous;
        this.next = next;
    }
    
    public Generic getElement() {
        return element;
    }
    public void setNext(LinkedNode<Generic> next){
        this.next = next;
    }
    public LinkedNode<Generic> getNext(){
        return this.next;
    }

    public void setPrevious(LinkedNode<Generic> previous) {
        this.previous = previous;
    }
    public LinkedNode<Generic> getPrevious() {
        return previous;
    }

    public void setBoth(LinkedNode<Generic> element) {
        this.previous = element;
        this.next = element;
    }
    public void setPrevNext(LinkedNode<Generic> e1, LinkedNode<Generic> e2) {
        this.previous = e1;
        this.next = e2;
    }

    @Override
    public String toString() {
        return String.format("Element: %s Prev: %s Next: %s", this.element.toString(), previous.getElement().toString(), next.getElement().toString());
    }
}