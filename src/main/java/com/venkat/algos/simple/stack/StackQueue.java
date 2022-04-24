package com.venkat.algos.simple.stack;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

public class StackQueue<T> extends AbstractQueue<T> implements Queue<T> {

    private Stack<T> enqueueStack;
    private Stack<T> dequeueStack;

    public StackQueue() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    /*
     * AbstractQueue<T> methods override
     */
    @Override
    public boolean offer(T val) {
        enqueueStack.push(val);
        return true;
    }

    @Override
    public synchronized T poll() {
        return isEmpty() ? null : dequeueStack.pop();
    }

    @Override
    public synchronized T peek() {
        return isEmpty() ? null : dequeueStack.peek();
    }

    /*
     * Extra overrides
     */
    @Override
    public boolean add(T val) {
        // Just calling offer, as we don't have Queue size constraints here...
        return offer(val);
    }

    @Override
    public T remove() {
        return Optional
                   .ofNullable(poll())
                   .orElseThrow(() -> new NoSuchElementException("Queue empty!"));
    }

    @Override
    public T element() {
        return Optional
                   .ofNullable(peek())
                   .orElseThrow(() -> new NoSuchElementException("Queue empty!"));
    }

    class SnapShotIterator implements Iterator<T> {

        private Iterator<T> ssIterator;

        SnapShotIterator() {
            List<T> snapShotList = new ArrayList<>(size());
            if (!dequeueStack.isEmpty()) {
                snapShotList.addAll(dequeueStack);
                Collections.reverse(snapShotList);
            }
            snapShotList.addAll(enqueueStack);
            ssIterator = snapShotList.iterator();
        }

        @Override
        public boolean hasNext() {
            return ssIterator.hasNext();
        }

        @Override
        public T next() {
            return ssIterator.next();
        }

    }

    /*
     * AbstractCollection<T> methods override
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return new SnapShotIterator();
    }

    @Override
    public synchronized int size() {
        return enqueueStack.size() + dequeueStack.size();
    }

    public String toString() {
        return String.format("Enqueue stack: %s, Dequeue stack: %s, Size: %d", enqueueStack, dequeueStack, size());
    }

    public synchronized boolean isEmpty() {
        adjustStacks();
    	return dequeueStack.isEmpty();
    }

    private void adjustStacks() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty())
                dequeueStack.push(enqueueStack.pop());
        }
    }

    public static void main(String[] args) {
        StackQueue<Integer> q1 = new StackQueue<>();
        for (int i = 1 ; i < 10 ; i++) {
            q1.add(i);
            System.out.format("%d: %s, Peek=%d, Remove=%d%n",
                i, q1, q1.element(), (i % 2 == 0) ? q1.remove() : null);
        }

        System.out.println("Iterating over queue");
        for (int n : q1) {
            System.out.println(n);
        }
    }

}
