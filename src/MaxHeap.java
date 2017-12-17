public class MaxHeap<T extends Comparable<T>> {

    T[] data;
    private int size;

    MaxHeap(int size) {
        data = (T[]) new Comparable[size + 1];
    }

    MaxHeap(T[] array) {
        data = (T[]) new Comparable[array.length + 1];
        size = array.length;
        System.arraycopy(array, 0, data, 1, array.length);

        for (int i = size / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    void insert(T t) {
        if (size < data.length) {
            data[size + 1] = t;
            size++;
            shiftUp(size);
        }
    }

    private void shiftUp(int index) {
        T r = data[index];

        int j = index / 2;
        while (j >= 1) {
            T c = data[j];
            if (r.compareTo(c) > 0) {
                data[index] = c;
                index = j;
                j /= 2;
            }
        }
        data[index] = r;
    }

    public T exarctMax() {
        T result = data[1];
        data[1] = data[size];
        size--;
        shiftDown(1);
        return result;
    }

    private void shiftDown(int index) {
        T t = data[index];

        int j = index;
        while (j * 2 <= size) {
            j = j * 2;

            if (j + 1 <= size && data[j + 1].compareTo(data[j]) > 0) j += 1;

            if (t.compareTo(data[j]) >= 0) break;

            data[index] = data[j];
            index = j;
        }

        data[index] = t;
    }

    int size() {
        return size;
    }

    public static void heapSort(int[] array) {

    }

    static int binarySearch(Name[] array, int targeID) {
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            Name m = array[mid];
            if (m.id == targeID) return mid;
            if (m.id < targeID) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    static int binarySearchFloor(Name[] array, int targeID, int stat) {
        int l = 0;
        int r = array.length - 1;
        int resultIndex = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            Name m = array[mid];
            if (m.id == targeID) {
                resultIndex = mid;
                r = mid - 1;
            } else if (m.id < targeID) l = mid + 1;
            else r = mid - 1;
        }
        if (resultIndex == -1 && stat > 0) resultIndex = binarySearchCeil(array, targeID - 1, stat - 1);
        return resultIndex;
    }

    static int binarySearchCeil(Name[] array, int targeID, int stat) {
        int l = 0;
        int r = array.length - 1;
        int resultIndex = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            Name m = array[mid];
            if (m.id == targeID) {
                resultIndex = mid;
                l = mid + 1;
            } else if (m.id < targeID) l = mid + 1;
            else r = mid - 1;
        }
        if (resultIndex == -1 && stat > 0) resultIndex = binarySearchFloor(array, targeID + 1, stat - 1);
        return resultIndex;
    }

    public static void testBinartSearch() {
        int n = 10;
        Name[] array = new Name[n];
        array[0] = new Name("姓名 " + 0, 1);
        array[1] = new Name("姓名 " + 1, 1);
        array[2] = new Name("姓名 " + 2, 1);
        array[3] = new Name("姓名 " + 3, 1);

        array[4] = new Name("姓名 " + 4, 3);
        array[5] = new Name("姓名 " + 5, 3);
        array[6] = new Name("姓名 " + 6, 3);

        array[7] = new Name("姓名 " + 7, 5);
        array[8] = new Name("姓名 " + 8, 5);
        array[9] = new Name("姓名 " + 9, 5);

//        System.out.println(array[binarySearch(array, 2)].name);
        System.out.println(array[binarySearchFloor(array, 2,1)].name);
        System.out.println(array[binarySearchCeil(array, 2,1)].name);
    }

    public static void main(String[] args) {
        testBinartSearch();

//        int n = 1000000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>(SortTestHelper.generateRandomArray(n, 0, n));
//        int[] array = new int[n];
//        for (int i = n - 1; i > 0; i--) {
//            array[i] = maxHeap.exarctMax();
//        }
//
//        boolean sorted = true;
//        for (int i = 0; i < array.length; i += 2) {
//            if (array[i] > array[i + 1]) sorted = false;
//        }
//
//        System.out.println(sorted);
//        int index = binarySearch(array, 34);
//        System.out.println(index);
//        System.out.println(array[index]);
    }

    static class Name {

        String name;
        int id;

        Name(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

}
