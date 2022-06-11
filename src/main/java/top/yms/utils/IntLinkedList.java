package top.yms.utils;


import java.util.NoSuchElementException;

public class IntLinkedList {
    private static class Node{
        int item;
        Node next;
        Node prev;

        Node(Node prev, int element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    transient int size = 0;

    transient Node first;

    transient Node last;

    protected transient int modCount = 0;

    public IntLinkedList() {

    }

    public IntLinkedList(int [] arrs) {
        this();
        if (arrs == null) {
            throw new NullPointerException("arrs is null");
        }
        int len = arrs.length;
        for(int i=0; i<len; i++) {
            add(arrs[i]);
        }
    }

    private void linkFirst(int e) {
        final Node  f = first;
        final Node  newNode = new Node(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    void linkLast(int e) {
        final Node  l = last;
        final Node  newNode = new Node(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }


    public int getFirst() {
        final Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }


    public int getLast() {
        final Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }


    public int removeFirst() {
        final Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    public int get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }


    public int removeLast() {
        final Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }


    public void addFirst(int e) {
        linkFirst(e);
    }


    public void addLast(int e) {
        linkLast(e);
    }


    private int unlinkFirst(Node f) {
        // assert f == first && f != null;
        final int element = f.item;
        final Node next = f.next;
       // f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }


    private int unlinkLast(Node l) {
        // assert l == last && l != null;
        final int element = l.item;
        final Node prev = l.prev;
       // l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }


    public int set(int index, int element) {
        checkElementIndex(index);
        Node x = node(index);
        int oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public boolean add(int e) {
        linkLast(e);
        return true;
    }

    public int size() {
        return size;
    }

    public int remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    int unlink(Node x) {
        // assert x != null;
        final int element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

       // x.item = null;
        size--;
        modCount++;
        return element;
    }

    Node node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }


}
