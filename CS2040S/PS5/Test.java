import java.util.Arrays;

class Test {

    public static int modify(int[] arr, int j) {
        for (int i : arr) {
            i = j;
        }
        return j;
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        int f = modify(arr, 5);
        System.out.println(Arrays.toString(arr) + " " + f);
    }
}