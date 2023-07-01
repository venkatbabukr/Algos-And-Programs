package com.venkat.algos.simple.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayLeadersFinder {
	
	public static ArrayList<Integer> leaders(int arr[], int n) {
		ArrayList<Integer> leaders = new ArrayList<>();
		int currentLeader = arr[n - 1];
		leaders.add(currentLeader);
		for (int i = n - 2; i > - 1; i--) {
			if (arr[i] > currentLeader) {
				currentLeader = arr[i];
				leaders.add(currentLeader);
			}
		}
		Collections.reverse(leaders);
		return leaders;
	}

}
