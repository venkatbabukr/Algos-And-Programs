package com.venkat.algos.simple.llists;

public class ListNode<E> {

    E val;
    public ListNode<E> next;

    public ListNode(E val) {
        this(val, null);
    }

    public ListNode(E val, ListNode<E> next) {
        this.val = val;
        this.next = next;
    }

    public E getVal() {
        return val;
    }

    public ListNode<E> getNext() {
        return next;
    }

}
