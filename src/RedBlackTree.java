public class RedBlackTree<Key extends Comparable<Key>, Value> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }

    }

    private int count;
    private Node root;

    public void put(Key key, Value val) {
        count = 0;
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node r, Key key, Value val) {
        count++;
        if (r == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(r.key);
        if (cmp == 0) r.val = val;
        else if (cmp < 0) r.left = put(r.left, key, val);
        else r.right = put(r.right, key, val);

        if (isRed(r.right) && !isRed(r.left)) r = rotateLeft(r);
        if (isRed(r.left) && !isRed(r.left.left)) r = rotateRight(r);
        if (isRed(r.right) && isRed(r.left)) flipColors(r);

        r.N = size(r.left) + size(r.right) + 1;
        return r;
    }

    private void flipColors(Node r) {
        r.color = RED;
        r.left.color = BLACK;
        r.right.color = BLACK;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return 1 + size(x.left) + size(x.right);
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        for (int i = 0; i < 100; i++) {
            tree.put(i, i + " vvv");
        }
        System.out.println(tree.root.right.val);
    }
}
