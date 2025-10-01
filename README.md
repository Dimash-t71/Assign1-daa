# Assign1-daa
Report: Algorithm Implementation and Testing

1. Introduction

This project implements and analyzes four fundamental algorithms:
	•	MergeSort – efficient sorting algorithm with divide-and-conquer.
	•	QuickSort – optimized variant with randomized pivot.
	•	Deterministic Select (Median of Medians) – used to find the k-th smallest element in linear time.
	•	Closest Pair of Points (Divide and Conquer) – finds the minimum distance between points in a 2D plane.




2. Algorithms Implemented

 MergeSort
	•	Recursively divides array into halves.
	•	Merges sorted halves back.
	•	Complexity: O(n log n) worst, average, best.
	•	Stable algorithm.

 QuickSort
	•	Random pivot chosen to reduce worst-case chance.
	•	Recursion depth minimized with tail recursion.
	•	Complexity: O(n log n) average, O(n²) worst-case.
	•	Faster than MergeSort in practice (better constants).

 Deterministic Select
	•	Uses Median of Medians to guarantee linear time.
	•	Finds k-th smallest element without fully sorting.
	•	Complexity: O(n) worst-case.
	•	Useful in large datasets where full sorting is unnecessary.

 Closest Pair of Points
	•	Problem: find the shortest distance between two points in a set.
	•	Solution: Divide & Conquer with strip merging.
	•	Complexity: O(n log n).
	•	Brute force version has O(n²), so this is much more efficient.



3. Implementation Details
	•	Language: Java 17
	•	Build tool: Maven
	•	Testing: JUnit 5
	•	Metrics collected:
	•	Execution time (ms)
	•	Number of comparisons
	•	Number of swaps
	•	Recursion depth

Structure of packages:
	•	com.assignment1.sort → MergeSort, QuickSort
	•	com.assignment1.select → Deterministic Select
	•	com.assignment1.closest → Closest Pair of Points
	•	com.assignment1.cli → Command line interface (CLI)
 
  
  
  Conclusion
	•	All four algorithms implemented successfully.
	•	The implementations are efficient and correct (verified with JUnit tests).
	•	Deterministic Select and Closest Pair showcase advanced divide-and-conquer techniques.
	•	The project demonstrates strong understanding of algorithm design, complexity analysis, and testing.

