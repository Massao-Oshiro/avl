class Node {
    public int data;
    public int height;
    public Node left;
    public Node right;
    
    public Node(int data) {
        this.data = data;
        this.height = -1;
        this.left = null;
        this.right = null;
    }
}

class Avl {
    public Node root;
    
    public Avl() {
        root = null;
    }
    
    private int height(Node n) { return (n == null ? 0 : n.height); }
    
    private int max(int a, int b) { return (a > b) ? a : b; }
    
    private int updateHeight(Node n) { return (max(height(n.left), height(n.right)) + 1); }
    
    private int getBalance(Node n) { return (n == null ? 0 : (height(n.right) - height(n.left))); }
    
    private Node rightRotation(Node n) {
        Node t = n.left;
        n.left = t.right;
        t.right = n;
        
        t.height = updateHeight(t);
        n.height = updateHeight(n);
        return t;
    }
    
    private Node leftRotation(Node n) {
        Node t = n.right;
        n.right = t.left;
        t.left = n;
        
        t.height = updateHeight(t);
        n.height = updateHeight(n);
        return t;
    }
    
    private Node doubleRightRotation(Node n) {
        n.left = leftRotation(n.left);
        return rightRotation(n);
    }
    
    private Node doubleLeftRotation(Node n) {
        n.right = rightRotation(n.right);
        return leftRotation(n);
    }
    
    private Node updateBalanceFactorRight(Node n) {
        n.height = updateHeight(n);
        if(getBalance(n) == 2) {
            if(getBalance(n.right) >= 0) n = leftRotation(n);
            else n = doubleLeftRotation(n);
        }
        return n;
    }
    
    private Node updateBalanceFactorLeft(Node n) {
        n.height = updateHeight(n);
        if(getBalance(n) == -2) {
            if(getBalance(n.left) >= 0) n = rightRotation(n);
            else n = doubleRightRotation(n);
        }
        return n;
    }
    
    public Node insert(Node n, int key) {
        if(n == null) n = new Node(key);
        else if(key < n.data) {
            n.left = insert(n.left, key);
            n = updateBalanceFactorLeft(n);
        } else {
            n.right = insert(n.right, key);
            n = updateBalanceFactorRight(n);
        }
        return n;
    }

    public boolean search(Node n, int key) {
        if(n == null) return false;
        else if(n.data == key) return true;
        else if(key < n.data) return search(n.left, key);
        return search(n.right, key);
    }
    
    public void traversalInOrder(Node n) {
        if(n == null) return;
        
        traversalInOrder(n.left);
        System.out.println(n.data);
        traversalInOrder(n.right);
    }
}

public class avl {
    public static void main(String[] args) {
        Avl a = new Avl();
        for(int i = 0; i < 10; i++) {
            a.root = a.insert(a.root, i);
        }
        a.traversalInOrder(a.root);
    }
}







