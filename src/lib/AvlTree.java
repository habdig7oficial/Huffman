package lib;

public class AvlTree<Generic extends Comparable<Generic>> {
    private Node<Generic> root = null;

    public void insert(Generic element){
        this.insert(element, this.root);
    } 

    private void insert(Generic element, Node<Generic> subtree){
        if (subtree == null) {
            subtree = new Node<Generic>(element);
            return;
        }
        else if(element.compareTo(subtree.getKey()) > 0){
            if (subtree.getRight().getKey() == null) 
                subtree.setRight(new Node<Generic>(element));
            else
                this.insert(element, subtree.getLeft()); 
        }
        else if(element.compareTo(subtree.getKey()) < 0){
                if (subtree.getLeft().getKey() == null) 
                    subtree.setLeft(new Node<Generic>(element));
                else
                    this.insert(element, subtree.getLeft());
        }
        else
            subtree.setCount();
    }
}