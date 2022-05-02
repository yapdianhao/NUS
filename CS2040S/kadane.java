class Kadane {

    public static void main(String[] args) {
        int[] arr = {4,7,-5,9,-7,2};
        System.out.println(kadane(arr));
    }

    static int kadane(int[] arr) {
        int globalBest = 0;
        int bestSoFar = 0;
        for (int i = 0; i < 6; i++) {
            if (bestSoFar + arr[i] > 0) {
                bestSoFar += arr[i];
            } else {
                bestSoFar = 0;
            }
            globalBest = bestSoFar > globalBest ? bestSoFar : globalBest;
        }
        return globalBest;
    }
}