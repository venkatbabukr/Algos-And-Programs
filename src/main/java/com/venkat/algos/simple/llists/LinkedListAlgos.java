package com.venkat.algos.simple.llists;

import java.util.Optional;

public class LinkedListAlgos<E> {

	/**
	 * Floyd's loop finding algo - Basically the one that uses fast and slow pointers!
     *
	 * @param head    Head of linked list
	 * @return        true if list has loop, false otherwise!
	 */
    public boolean hasLoop(ListNode<E> head) {
        boolean listHasLoop = false;
        if (head != null) {
            ListNode<E> fastPtr = head, slowPtr = head;
            do {
                slowPtr = slowPtr.next;
                fastPtr = Optional.ofNullable(fastPtr.next).map(n -> n.next).orElse(null);
            } while (fastPtr != null && fastPtr != slowPtr);
            listHasLoop = slowPtr == fastPtr; // Donot modify this condition! If list doesn't have loop,
                                              // fastPtr will always be the first to reach end of list!
        }
        return listHasLoop;
    }

    public ListNode<E> removeLoop(ListNode<E> head) {
        if (head != null) {
            ListNode<E> fastPtr = head, slowPtr = head;
            do {
                slowPtr = slowPtr.next;
                fastPtr = Optional.ofNullable(fastPtr.next).map(n -> n.next).orElse(null);
            } while (fastPtr != null && fastPtr != slowPtr);
            if (slowPtr == fastPtr) {
            	// List has loop!
                if (fastPtr == head) {
                	while (slowPtr.next != head) {
                		slowPtr = slowPtr.next;
                	}
                } else {
                	ListNode<E> t = head;
                    while (t != fastPtr) {
                    	slowPtr = fastPtr;
                    	fastPtr = fastPtr.next;
                    	t = t.next;
                    }
                }
                slowPtr.next = null;
            }
        }
    	return head;
    }

	/**
     * Given a pointer to a random node in linked list, remove that node.
     * Solution: Copy the data from following nodes successively. We can�t delete the node, because we don�t have access to the previous node.
     * 
     * This won�t work if the pointer is given to last node! In such case, we have to maintain an extra empty node - signaling the end of linked list, and that empty node can�t be deleted.
     * Answer link: https://www.geeksforgeeks.org/in-a-linked-list-given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
     * 
     * @param head    head of linked list
	 * @param r       ramdom node that needs to be deleted
	 * @return        head of linked list after deleting random node r
	 */
    // TODO: Modify the algo to remove last node if random pointer r points to last node in list!
    public ListNode<E> remove(ListNode<E> head, ListNode<E> r) {
        if (head == null)
            throw new IllegalArgumentException("List head can't be null!");
        if (r != null) {
            if (r == head) {
                head = head.next;
            } else {
                while (r.next != null) {
                    r.val = r.next.val;
                    r = r.next;
                }
                r.next = null;
            }
        }
        return head;
    }

    public ListNode<E> removeEnhanced(ListNode<E> head, ListNode<E> r) {
        if (head == null)
            throw new IllegalArgumentException("List head can't be null!");
        if (r != null) {
            if (r == head) {
                head = head.next;
            } else {
            	ListNode<E> rPrev = head;
            	while (rPrev != null && rPrev.next != r) rPrev = rPrev.next;
            	if (rPrev != null) {
            		rPrev.next = r.next;
            	}
            }
        }
        return head;
    }

    public ListNode<E> findMid(ListNode<E> head) {
    	ListNode<E> midPtr, jumpPtr;
    	midPtr = jumpPtr = head;
    	while (jumpPtr != null && jumpPtr.next != null) {
    		midPtr = midPtr.next;
    		jumpPtr = jumpPtr.next.next;
    	}
    	return midPtr;
    }

    public ListNode<E> reverse(ListNode<E> head) {
    	ListNode<E> prev = null, curr = head, next = null;
    	while (curr != null) {
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	return prev;
    }

    public boolean isPallindrome(ListNode<E> head) {
    	return false;
    }

}
