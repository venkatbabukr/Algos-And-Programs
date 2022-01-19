package com.venkat.algos.simple.llists;

class RPTRListNode<T> {
    public T val;
    public RPTRListNode<T> next;
    public RPTRListNode<T> randomNext;

    public RPTRListNode(T val) {
        this(val, null, null);
    }

    public RPTRListNode(T val, RPTRListNode<T> next) {
        this(val, next, null);
    }

    public RPTRListNode(T val, RPTRListNode<T> next, RPTRListNode<T> rNext) {
        this.val = val;
        this.next = next;
        this.randomNext = rNext;
    }
}

public class CloneLLWithRandomPointer<T> {

    public RPTRListNode<T> clone(RPTRListNode<T> head) {
        if (head == null) return null;

        // Create clone nodes with next = original.next and make original.next point to the clone...
        for (RPTRListNode<T> iter = head, iterNext = null; iter != null; iter = iterNext) {
            iterNext = iter.next;
        	iter.next = new RPTRListNode<>(iter.val, iter.next);
        }

        // Setup the randomNext in the clones now...
        for (RPTRListNode<T> iter = head; iter != null; iter = iter.next != null ? iter.next.next : null) {
        	iter.next.randomNext = iter.randomNext.next;
        }

        // Set the head of new clone and fix up next pointers...
        RPTRListNode<T> cloneHead = head.next;
        for (RPTRListNode<T> iter = head, cloneIter = cloneHead; iter != null; iter = iter.next, cloneIter = cloneIter.next) {
        	iter.next = cloneIter.next;
        	cloneIter.next = cloneIter.next != null ? cloneIter.next.next : null;
        }
        return cloneHead;
    }

}
