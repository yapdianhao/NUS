/*
Name: Yap Dian Hao
Time Complexity: ⍬(n log n)

Prove the correctness of your algorithm here:
This algorithm first calculates the differences between the time needed for Alice and Bob to solve for each question,
sorts the differences from largest to smallest, and greedily selects the lesser amount of time between Alice and Bob
needed to sort the question. 

Proof:
- The greedy algorithm, A, first sorts the pairs (a[i], b[i]) according to their differences from maximum to minimum
and selects the minimum of (a[i], b[i]) is both Alice and Bob's done tasks are < N. Let the chosen values to be
{x1, x2, ..... x2N}.
- Let the optimal algorithm be O, and the chosen pair be {x1, x2, ... xi, ...x2N}. Assume the greedy algorithm A is not 
optimal, which A != O. There exist xi which is in O but not in A. For the time to be minimum, since the difference between
a[i], b[i] is sorted, it will be suboptimal to select the larger value. Therefore, we can swap xi to the value in A, and 
obtain an even optimal algorithm O'.
- This indicates that O' is more optimal than O, which contradicts that O is an optimal solution.
- Therefore, A is optimal.
*/

import java.util.*;

class TaskA {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] a = new int[N * 2];
		int[] b = new int[N * 2];
		for (int i = 0; i < N * 2; i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		Pair[] arr = new Pair[N * 2];
		for (int i = 0; i < 2 * N; i++) {
			Pair pair = new Pair(a[i], b[i]);
			arr[i] = pair;
		}
		long answer = 0;
		Arrays.sort(arr, Collections.reverseOrder()); // n log n for sorting
		// System.out.println(Arrays.toString(arr));
		int countA = 0;
		int countB = 0;
		for (int i = 0; i < 2 * N; i++) {
			int chosen;
			Pair curr = arr[i];
			if (curr.a < curr.b) {
				if (countA < N) {
					chosen = curr.a;
					countA++;
				} else {
					chosen = curr.b;
					countB++;
				}
			} else {
				if (countB < N) {
					chosen = curr.b;
					countB++;
				} else {
					chosen = curr.a;
					countA++;
				}
			}
			answer += chosen;
			// System.out.println(chosen);
		}
		sc.close();
		// System.out.println("++++++++++++");
		// System.out.println(countA);
		// System.out.println(countB);
		System.out.println(answer);
	}

	public static class Pair implements Comparable<Pair> {

		int a;
		int b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair ot) {
			return Math.abs(this.a - this.b) - Math.abs(ot.a - ot.b);
		}

		public String toString() {
			return "(" + this.a + ", " + this.b + ")";
		}
	}
}