package structures;

import java.util.Comparator;

public class BinaryTreeOnGenerics<T, E> {
    private Node<T, E> root;
    private final Comparator<T> comparator;

    public BinaryTreeOnGenerics(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private static class Node<T, E> {
        private final T key;
        private final E inf;
        private Node<T, E> left;
        private Node<T, E> right;

        private Node(T key, E inf) {
            this.key = key;
            this.inf = inf;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", inf=" + inf +
                    ", left=" + ((left == null) ? "null" : left.key) +
                    ", right=" + ((right == null) ? "null" : right.key) +
                    '}';
        }
    }

    public void add(T key, E inf) {
        if (root == null) {
            root = new Node<T, E>(key, inf);
        } else {
            Node<T, E> parent = findParentToNewNode(key);
            if (comparator.compare(key, parent.key) < 0) {
                parent.left = new Node<T, E>(key, inf);
            } else {
                parent.right = new Node<T, E>(key, inf);
            }
        }
    }

    private Node<T, E> findParentToNewNode(T key) {
        return findParentToNewNode(root, key);
    }
    private Node<T, E> findParentToNewNode(Node<T, E> current, T key) {
        if (comparator.compare(key, current.key) < 0) {
            if (current.left == null) {
                return current;
            } else {
                return findParentToNewNode(current.left, key);
            }
        } else {
            if (current.right == null) {
                return current;
            } else {
                return findParentToNewNode(current.right, key);
            }
        }
    }

    private Node<T, E> findParentToRemoveNode(T key) {
        return findParentToRemoveNode(root, key);
    }
    private Node<T, E> findParentToRemoveNode(Node<T, E> current, T key) {
        if (current != null) {
            if (comparator.compare(key, current.key) < 0) {
                if (!(current.left != null && comparator.compare(current.left.key, key) == 0)) {
                    return findParentToRemoveNode(current.left, key);
                }
            } else if (comparator.compare(key, current.key) > 0) {
                if (!(current.right != null && comparator.compare(current.right.key, key) == 0)) {
                    return findParentToRemoveNode(current.right, key);
                }
            }
        }
        return current;
    }

    private Node<T, E> findParentToMinNode(Node<T, E> current) {
        if (current.left.left == null) {
            return current;
        }
        return findParentToMinNode(current.left);
    }

    public E findByKey(T key) {
        return findByKey(root, key);
    }
    private E findByKey(Node<T, E> current, T key) {
        if (current != null && comparator.compare(current.key, key) < 0) {
            return findByKey(current.left, key);
        } else {
            if (current != null && comparator.compare(current.key, key) > 0) {
                return findByKey(current.right, key);
            } else {
                return current == null ? null : current.inf;
            }
        }
    }

    public boolean checkKey(T key) {
        return findByKey(root, key) != null;
    }

    public void deleteNode(T key) {
        if (root == null) {
            return;
        }

        if (comparator.compare(root.key, key) == 0) {
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            if (root.left == null || root.right == null) {
                root = (root.left == null) ? root.right : root.left;
                return;
            }

            Node<T, E> min = root.right;
            Node<T, E> pMin = root;

            if (min.left != null){
                pMin = findParentToMinNode(min);
                min = pMin.left;
            }
            pMin.right = min.right;

            min.right = root.right;
            min.left = root.left;
        }

        Node<T, E> parent = findParentToRemoveNode(key);
        if (parent == null) {
            return;
        }
        boolean isRide = parent.left == null || comparator.compare(key, parent.left.key) != 0;
        Node<T, E> element = isRide ? parent.right : parent.left;

        if (element.left == null && element.right == null) {
            if (isRide) {
                parent.right = null;
            } else {
                parent.left = null;
            }
            return;
        }

        if (element.left == null || element.right == null) {
            Node<T, E> tmp = (element.left == null) ? element.right : element.left;
            if (isRide) {
                parent.right = tmp;
            } else {
                parent.left = tmp;
            }
            return;
        }

        Node<T, E> min = element.right;
        Node<T, E> pMin = element;

        if (min.left != null){
            pMin = findParentToMinNode(min);
            min = pMin.left;
        }
        pMin.right = min.right;

        min.right = element.right;
        if (isRide) parent.right = min;
        else parent.left = min;
        min.left = element.left;
    }

    public void bypassSymmetric(){
        System.out.print("bypassSymmetric: ");
        bypassSymmetric(root);
        System.out.println();
    }
    private void bypassSymmetric(Node<T, E> current){
        if (current != null){
            bypassSymmetric(current.left);
            System.out.print(current.key + ", ");
            bypassSymmetric(current.right);
        }
    }

    public void bypassDirect(){
        System.out.print("bypassDirect: ");
        bypassDirect(root);
        System.out.println();
    }
    private void bypassDirect(Node<T, E> current){
        if (current != null){
            System.out.print(current.key + ", ");
            bypassDirect(current.left);
            bypassDirect(current.right);
        }
    }

    public void bypassReverse(){
        System.out.print("bypassReverse: ");
        bypassReverse(root);
        System.out.println();
    }
    private void bypassReverse(Node<T, E> current){
        if (current != null){
            bypassReverse(current.left);
            bypassReverse(current.right);
            System.out.print(current.key + ", ");
        }
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
