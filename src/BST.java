public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value value;
        Node left, right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private Node root;
    private int count;

    public void insert(Key key, Value value) {
        count++;
        if (root == null) root = new Node(key, value);
        else {
            Node temp = root;
            while (temp != null) {
                int r = temp.key.compareTo(key);
                if (r == 0) {
                    temp.value = value;
                    return;
                } else if (r > 0 && temp.left == null) temp.left = new Node(key, value);
                else if (r < 0 && temp.right == null) temp.right = new Node(key, value);
                else if (r > 0) temp = temp.left;
                else temp = temp.right;
            }
        }
    }

    public Value get(Key key) {
        Node temp = root;
        while (temp != null) {
            int r = temp.key.compareTo(key);
            if (r == 0) {
                return temp.value;
            } else if (r > 0) temp = temp.left;
            else temp = temp.right;
        }

        return null;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            bst.insert(i, "第 " + i + " 个");
        }

        System.out.println(bst.get(5));
    }
}
