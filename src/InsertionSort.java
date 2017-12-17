public class InsertionSort {

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {

            for (int j = i; j > 0 && (array[j] < array[j - 1]); j--) {
                SelectionSort.swap(array, j - 1, j);
            }
        }
    }

}
