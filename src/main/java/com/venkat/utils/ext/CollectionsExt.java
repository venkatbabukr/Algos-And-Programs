package com.venkat.utils.ext;

import static java.util.stream.Collectors.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class CollectionsExt {

	public static <T extends Comparable<T>> Optional<T> min(Collection<T> col) {
		return min(col, Comparator.naturalOrder());
	}

	public static <T> Optional<T> min(Collection<T> col, Comparator<T> comparator) {
		return Optional.ofNullable(col).flatMap(c -> c.stream().min(comparator));
	}

	public static <T extends Comparable<T>> Optional<T> max(Collection<T> col) {
		return max(col, Comparator.naturalOrder());
	}

	public static <T> Optional<T> max(Collection<T> col, Comparator<T> comparator) {
		return Optional.ofNullable(col).flatMap(c -> c.stream().max(comparator));
	}

	public static <T extends Comparable<T>> List<T> minK(Collection<T> col, int k) {
		return minK(col, k, Comparator.naturalOrder());
	}

	public static <T> List<T> minK(Collection<T> col, int k, Comparator<T> comparator) {
		return col.stream().sorted(comparator).limit(k).collect(toList());
	}

	public static <T extends Comparable<T>> List<T> maxK(Collection<T> col, int k) {
		return maxK(col, k, Comparator.naturalOrder());
	}

	public static <T> List<T> maxK(Collection<T> col, int k, Comparator<T> comparator) {
		return col.stream().sorted(comparator.reversed()).limit(k).collect(toList());
	}

	public static <T extends Comparable<T>> List<T> minKEnhanced(Collection<T> col, int k) {
		return 	minKEnhanced(col, k, Comparator.naturalOrder());
	}

	public static <T> List<T> minKEnhanced(Collection<T> col, int k, Comparator<T> comparator) {
		PriorityQueue<T> minElementsQueue = new PriorityQueue<>(k, comparator.reversed());
		for (T val : col) {
			if (minElementsQueue.size() < k || comparator.compare(val, minElementsQueue.peek()) < 0) {
				// Add the new smaller element to Queue
				minElementsQueue.offer(val);
				if (minElementsQueue.size() >= k) {
					// Remove the highest smaller element from Queue
					minElementsQueue.poll();
				}
			}
		}
		return minElementsQueue.stream().collect(toList());
	}

	public static <T extends Comparable<T>> List<T> maxKEnhanced(Collection<T> col, int k) {
		return 	maxKEnhanced(col, k, Comparator.naturalOrder());
	}

	public static <T> List<T> maxKEnhanced(Collection<T> col, int k, Comparator<T> comparator) {
		PriorityQueue<T> maxElementsQueue = new PriorityQueue<>(k, comparator);
		for (T val : col) {
			if (maxElementsQueue.size() < k || comparator.compare(val, maxElementsQueue.peek()) > 0) {
				// Add the new smaller element to Queue
				maxElementsQueue.offer(val);
				if (maxElementsQueue.size() >= k) {
					// Remove the highest smaller element from Queue
					maxElementsQueue.poll();
				}
			}
		}
		return maxElementsQueue.stream().collect(toList());
	}

}
