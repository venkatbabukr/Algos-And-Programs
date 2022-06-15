package com.venkat.algos.simple.llists;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class LinkedList<E> {

    ListNode<E> head;

    public LinkedList(ListNode<E> head) {
        this.head = head;
    }

    private static <E> ListNode<E> buildListNodesFromStream(Stream<E> str) {
        return buildListNodesFromStream(null, str);
    }

    private static <E> ListNode<E> buildListNodesFromStream(ListNode<E> head, Stream<E> str) {
        if (head == null) head = new ListNode<>(null);
        str.sequential()
           .map(val -> new ListNode<>(val))
           .reduce(head, (n1, n2) -> {
               n1.next = n2;
               return n2;
           });
        return head.next;
    }

    public static <E> LinkedList<E> valueOf(E[] arr) {
        ListNode<E> head = null;
        if (arr != null) {
	        head = buildListNodesFromStream(Arrays.stream(arr));
        }
        return new LinkedList<>(head);
    }

    public static <E> LinkedList<E> valueOf(Collection<E> col) {
        ListNode<E> head = null;
        if (col != null) {
	        head = buildListNodesFromStream(col.stream());
        }
        return new LinkedList<>(head);
    }

}
