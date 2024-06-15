import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];

        // Combine profits and capital into a single array for easier sorting
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Sort projects based on the capital required to start them
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        // Use a max-heap (priority queue) to store the profits of feasible projects
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int index = 0;
        // Iterate up to k times to select projects
        for (int i = 0; i < k; i++) {
            // Add all feasible projects to the max-heap
            while (index < n && projects[index][0] <= w) {
                maxHeap.offer(projects[index][1]);
                index++;
            }

            // If the heap is empty, no more projects can be selected
            if (maxHeap.isEmpty()) {
                break;
            }

            // Select the project with the maximum profit
            w += maxHeap.poll();
        }

        return w;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int k1 = 2, w1 = 0;
        int[] profits1 = {1, 2, 3};
        int[] capital1 = {0, 1, 1};
        System.out.println(solution.findMaximizedCapital(k1, w1, profits1, capital1)); // Output: 4

        // Test case 2
        int k2 = 3, w2 = 0;
        int[] profits2 = {1, 2, 3};
        int[] capital2 = {0, 1, 2};
        System.out.println(solution.findMaximizedCapital(k2, w2, profits2, capital2)); // Output: 6
    }
}
