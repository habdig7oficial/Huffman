package lib;

public class Node <Generic> {
    private Generic key = null; 
    private Node<Generic> left = null, right = null;

    private boolean isRed;
    private int count = 0;

    public Node(){}

    public Node(Generic key){
        this.key = key; left = null; right = null; isRed = true;
    }

    public Node(Generic key, boolean color){
        this.key = key; left = null; right = null; isRed = color;
    }


    public Generic getKey() {
        return key;
    }
    public void setKey(Generic key) {
        if (key.equals(this.key)) {
            count++;
        }
        else
            this.key = key;
    }
    public Node<Generic> getLeft() {
        return left;
    }
    public void setLeft(Node<Generic> left) {
        this.left = left;
    }
    public Node<Generic> getRight() {
        return right;
    }
    public void setRight(Node<Generic> right) {
        this.right = right;
    }

    public void setisRed(boolean isRed) {
        this.isRed = isRed;
    }

    public boolean getisRed() {
        return isRed;
    }

    public boolean getisBlack() {
        return !isRed;
    }

    public int getCount() {
        return count;
    }
    public void setCount() {
        this.count++;
    }

    public String toString() {
        return this.key.toString();
    };
}