public class SelectionSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            swap(array, i, minIndex);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {10, 9,8,7,6,5,4,3,2,1,0};
        InsertionSort.sort(array);

        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
