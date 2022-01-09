package com.venkat.algos.leet;

class ListNode {
	int val;
	ListNode next;
	
	ListNode(int v) {
		this.val = v;
	}
	
	ListNode(int v, ListNode n) {
		this.val = v;
		this.next = n;
	}
}

/**
 * Leet code problem - https://leetcode.com/problems/add-two-numbers
 * 
 * @author vbkomarl
 */
public class LinkedListSum {

	public ListNode sum(ListNode l1, ListNode l2) {
		int sum = 0;
		ListNode sumListHead = new ListNode(sum);
		ListNode sumListIter = sumListHead;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			sumListIter.next = new ListNode(sum % 10);
			sumListIter = sumListIter.next;
			sum /= 10;
		}
		if (sum > 0) {
			sumListIter.next = new ListNode(sum);
		}
		return sumListHead.next;
	}

	/*
	 * Most efficient because the loops doesn't have if... else, the way LinkedListSum#sum has above...
	 */
	public ListNode mostEfficientSum(ListNode l1, ListNode l2) {
		int sum = 0;
		ListNode sumListHead = new ListNode(sum);
		ListNode sumListIter = sumListHead;
		for (; l1 != null && l2 != null; l1 = l1.next, l2 = l2.next, sumListIter = sumListIter.next) {
			sum += (l1.val + l2.val);
			sumListIter.next = new ListNode(sum % 10);
			sum /= 10;
		}
		for (; l1 != null; l1 = l1.next, sumListIter = sumListIter.next) {
			sum += l1.val;
			sumListIter.next = new ListNode(sum % 10);
			sum /= 10;
		}
		for (; l2 != null; l2 = l2.next, sumListIter = sumListIter.next) {
			sum += l2.val;
			sumListIter.next = new ListNode(sum % 10);
			sum /= 10;
		}
		if (sum > 0) {
			sumListIter.next = new ListNode(sum);
		}
		return sumListHead.next;
	}

}
