/*package lib;

public class RedBlackTree<Generic extends Comparable<Generic>> {
    private Node<Generic> root = null;
    public void insert(Generic element){

        if (this.root == null) {
            this.root = new Node<Generic>(element, true);
            return;
        }

        Node<Generic> subtree = this.root, ptr = null;
    
        while (subtree != null) {
            ptr = subtree;
            if (element.compareTo(subtree.getKey()) > 0)
                subtree = subtree.getRight();
            else if (element.compareTo(subtree.getKey()) < 0)
                subtree = subtree.getLeft();
            else{
                subtree.setCount();
                if(subtree.getRight() != null)
                    if(subtree.getRight().getCount() < subtree.getCount())
                        System.out.printf("\'%s\' - %d\tRight: \'%s\' - %d\n", subtree.getKey(), subtree.getCount(), subtree.getRight().getKey(), subtree.getRight().getCount());
                return;
            }
        }
        if (element.compareTo(ptr.getKey()) > 0)
            ptr.setRight(new Node<Generic>(element));
        else
            ptr.setLeft(new Node<Generic>(element));
    }

    public String walkPreOrder() {
        return this.walkPreOrder(this.root);
    }

    private String walkPreOrder(Node<Generic> node) {
        if (node == null)
            return "\n";
        
        return "( '" + node.getKey() + "' " + String.valueOf(node.getCount()) + ") " + walkPreOrder(node.getLeft())+  " " + walkPreOrder(node.getRight());
    }

} */