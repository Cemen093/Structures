package structures;

public class LinkedList<T> {
    Node<T> first;
    Node<T> last;

    public void addFirst(T element) {
        if (first == null) {
            first = last = new Node<T>(element);

        } else {
            first = new Node<T>(first, null, element);
            first.next.prev = first;
        }
    }

    public void addLast(T element) {
        if (last == null) {
            first = last = new Node<T>(element);
        } else {
            last = new Node<T>(null, last, element);
            last.prev.next = last;
        }
    }

    public T removeFirst() {
        T el = null;
        if (first != null) {
            el = first.element;
            if (first == last) {
                first = last = null;
            } else {
                first = first.next;
                first.prev = null;
            }
        }
        return el;
    }

    public T removeLast() {
        T el = null;
        if (last != null) {
            el = last.element;
            if (first == last) {
                first = last = null;
            } else {
                last = last.prev;
                last.next = null;
            }
        }
        return el;
    }

    public T getFromKey(T key) {
        Node<T> current = first;
        while (current != null && !current.element.equals(key)) {
            current = current.next;
        }
        return current == null ? null : current.element;
    }

    public T removeFromKey(T key){
        Node<T> current = first;
        if (current != null){
            while (current!= null && !current.element.equals(key)){
                current = current.next;
            }
        }
        if (current!= null && current.element.equals(key)){
            if (current.prev != null) {current.prev.next = current.next;}
            if (current.next != null) {current.next.prev = current.prev;}
        }

        return current == null ? null : current.element;
    }

    private class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T element;

        public Node(T element) {
            this.element = element;
        }

        public Node(Node<T> next, Node<T> prev, T element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", el = " + element +
                    '}';
        }
    }

    @Override
    public String toString() {
        String str = "LinkedList = {";
        Node<T> current = first;
        while (current != null){
            str += current.element.toString() + ", ";
            current = current.next;
        }
        str += "}";
        return str;
    }
}
