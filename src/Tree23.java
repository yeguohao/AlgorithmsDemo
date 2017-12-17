public class Tree23<K extends Comparable<K>, V> {

    private class Node {
        K leftKey, rightKey;
        V lval, rval;
        Node left, right, mid;
        Node parent;
    }

    private Node root;

    public V get(K key) {
        Node result = get(root, key);
        if (result == null) return null;
        else if (result.leftKey.compareTo(key) == 0) {
            return result.lval;
        } else return result.rval;
    }

    private Node get(Node r, K key) {
        if (r == null) {
            return null;
        }
        int c = r.leftKey.compareTo(key);
        if (c == 0) return r;
        else if (c > 0) {
            Node result = get(r.left, key);
            return result == null ? r : result;
        } else {
            c = r.rightKey.compareTo(key);
            if (c == 0) return r;
            else if (c < 0) {
                Node result = get(r.right, key);
                return result == null ? r : result;
            }
        }
        Node result = get(r.mid, key);
        return result == null ? r : result;
    }

    public void put(K key, V val) {

    }

    private void put(Node r, K key, V val) {
        if (key.compareTo(r.leftKey) == 0) r.lval = val;
        if (key.compareTo(r.rightKey) == 0) r.rval = val;

        int i = 0;
        if (key.compareTo(r.rightKey) < 0 && key.compareTo(r.leftKey) > 0) {
            if (r.mid == null) i = 1;
            i = 2;
        } else if (key.compareTo(r.rightKey) > 0) {
            if (r.right == null) i = 3;
            i = 4;
        } else if (key.compareTo(r.leftKey) < 0) {
            if (r.left == null) i = 5;
            i = 6;
        }

        if (i == 2) put(r.mid, key, val);
        else if (i == 4) put(r.right, key, val);
        else if (i == 6) put(r.left, key, val);
        else if (i == 1) {
            if (r.leftKey != null && r.rightKey == null) {
                if (r.leftKey.compareTo(key) > 0) {
                    r.rightKey = r.leftKey;
                    r.rval = r.lval;
                    r.leftKey = key;
                    r.lval = val;
                } else {
                    r.rightKey = key;
                    r.rval = val;
                }
            } else if (r.leftKey == null && r.rightKey != null) {
                if (r.rightKey.compareTo(key) > 0) {
                    r.leftKey = key;
                    r.lval = val;
                } else {
                    r.leftKey = r.rightKey;
                    r.lval = r.rval;
                    r.rightKey = key;
                    r.rval = val;
                }
            }

            if (r.leftKey != null && r.rightKey != null) {
                if (r.parent == null) {
                    Node mid = new Node();
                    mid.leftKey = key;
                    mid.lval = val;

                    Node left = new Node();
                    left.leftKey = r.leftKey;
                    left.lval = r.lval;
                    left.left = r.left;
                    left.parent = mid;

                    Node right = new Node();
                    right.rightKey = r.rightKey;
                    right.rval = r.rval;
                    right.right = r.right;
                    right.parent = mid;

                    mid.left = left;
                    mid.right = right;

                    root = mid;
                } else if (r.parent != null && r.parent.leftKey != null && r.parent.rightKey != null) {

                }
            }
        }
    }

}
