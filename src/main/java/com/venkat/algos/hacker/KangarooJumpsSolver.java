package com.venkat.algos.hacker;

/**
 * Solution for kangaroo numberline jumps problem
 * https://www.hackerrank.com/challenges/kangaroo/problem
 * 
 * The crux is to find out if the two lines x1 + j * v1 and x2 + j * v2 intersect, i.e.
 * there exists an integer j such that
 *   x1 + j * v1 = x2 + j * v2
 *   
 *   or j = (x2 - x1) / (v1 - v2)
 *
 * and in java to check if the fraction/double number is integer just add
 * j % 1 == 0
 *
 */
public class KangarooJumpsSolver {

	class IntersectionPoint {
		double j;
		
		public IntersectionPoint(double j) {
			this.j = j;
		}
		
		public boolean intersectPossible() {
			return j > 0.0 && j % 1 == 0;
		}
		
		public String toString() {
			return String.format("{j=%f, intersectPossible: %s}", j, intersectPossible());
		}
	}

	public IntersectionPoint kangaroo(int x1, int v1, int x2, int v2) {
		double j = 0;
		if (v1 == v2) {
			j = x1 == x2 ? x1 : -1;
		} else {
		    j = (x2 - x1) * 1.0 / (v1 - v2);
		}
		return new IntersectionPoint(j);
	}
	
	public static void main(String[] args) {
		KangarooJumpsSolver solver = new KangarooJumpsSolver();
		int x1, v1, x2, v2;
		x1 = 0; v1 = 3; x2 = 4; v2 = 2;
		System.out.format("Intersection point for x1=%d, v1=%d, x2=%d, v2=%d: %s%n", x1, v1, x2, v2, solver.kangaroo(x1, v1, x2, v2));
		x1 = 0; v1 = 2; x2 = 5; v2 = 3;
		System.out.format("Intersection point for x1=%d, v1=%d, x2=%d, v2=%d: %s%n", x1, v1, x2, v2, solver.kangaroo(x1, v1, x2, v2));
		x1 = 3; v1 = 3; x2 = 2; v2 = 6;
		System.out.format("Intersection point for x1=%d, v1=%d, x2=%d, v2=%d: %s%n", x1, v1, x2, v2, solver.kangaroo(x1, v1, x2, v2));
	}
}
