package tree;

public class Node <Generic> {
    private Generic key = null; 
    private Node<Generic> left = null, right = null;

    private int balance;
    private int count = 0;

    public Node(){}

    public Node(Generic v){
        key = v; left = null; right = null;
    }

    public Generic getKey() {
        return key;
    }
    public void setKey(Generic key) {
        this.key = key;
    }
    public Node<Generic> getLeft() {
        return left;
    }
    public void setLeft(Node<Generic> left) {
        this.left = left;
        this.setBalance();
    }
    public Node<Generic> getRight() {
        return right;
    }
    public void setRight(Node<Generic> right) {
        this.right = right;
        this.setBalance();
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(){
        if(this.left == null && this.right == null)
            this.balance = 0;
        else if(this.left == null)
            this.balance = this.right.getBalance() + 1;
        else if(this.right == null)
            this.balance = this.left.getBalance() - 1;
        else
            this.balance = this.right.getBalance() + this.left.getBalance();

        System.out.println(this.getBalance());
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