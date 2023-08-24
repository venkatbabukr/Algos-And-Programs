package com.venkat.algos.hacker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMinAndMaxKElements {
	
	public List<Integer> findMinK(List<Integer> numbers, int k) {
		List<Integer> kMinsList = null;

		if (numbers.size() > k) {
			// Have the mins sorted with maximum most on top of the list, so that deleteMin(queue) will
			// delete the biggest number.
			PriorityQueue<Integer> kMins = new PriorityQueue<>(k, Comparator.reverseOrder());
			for (Integer n : numbers) {
				if (kMins.size() < k || n < kMins.peek()) {
					// Add n to priority queue
					kMins.offer(n);
					if (kMins.size() > k) {
						// Remove the maximum most
						kMins.poll();
					}
				}
			}
			kMinsList = new ArrayList<>(kMins);
		} else {
			kMinsList = numbers;
		}
		return kMinsList;
	}
	
	public List<Integer> findMaxK(List<Integer> numbers, int k) {
		List<Integer> kMaxList = null;

		if (numbers.size() > k) {
			// Have the mins sorted with maximum most on top of the list, so that deleteMin(queue) will
			// delete the biggest number.
			PriorityQueue<Integer> kMaxes = new PriorityQueue<>(k);
			for (Integer n : numbers) {
				if (kMaxes.size() < k || n > kMaxes.peek()) {
					// Add n to priority queue
					kMaxes.offer(n);
					if (kMaxes.size() > k) {
						// Remove the maximum most
						kMaxes.poll();
					}
				}
			}
			kMaxList = new ArrayList<>(kMaxes);
		} else {
			kMaxList = numbers;
		}
		return kMaxList;
	}
	
	public static void main(String[] args) {
		FindMinAndMaxKElements finder = new FindMinAndMaxKElements();
		int n = 10, k = 3;
		List<Integer> numbers = IntStream.range(0, n).boxed().collect(Collectors.toList());
		System.out.format("findMinK(%1$s, %2$d)=%3$s, findMaxK(%1$s, %2$d)=%4$s%n", numbers, k,
				finder.findMinK(numbers, k), finder.findMaxK(numbers, k));

		Random randInts = new Random();
		n = 5;
		k = 2;
		// randInts.ints(n).collect(ArrayList::new, List::add, (l1, l2) -> l1.addAll(l2));
		numbers = randInts.ints(n, 1, 100).boxed().collect(Collectors.toList());
		System.out.format("findMinK(%s, %d)=%s", numbers, k,
							finder.findMinK(numbers, k));
	}

}
