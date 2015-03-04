package algorithms;

/**
 *                            The QuickSort Algorithm
 * 1. Partition the array into a left sub array and a right sub array, in which the items
 *    items in the left sub-array are smaller than the pivot and the items on the right
 *    sub array are greater than the pivot.
 *
 * 2. Recursively call the QuickSort to sort the left-sub array.
 *
 * 3. Recursively call the QuickSort to sort the right-sub array.
 *
 */
public class quickSortSelf {
    private static int array[];

    quickSortSelf(){
    }
    public static void sortArray(int[] array){
        quickSortSelf.array = array;
        quickSort(0,quickSortSelf.array.length-1);
    }
    public static void quickSort(int left, int right){

        if(left>=right){
            return;
        }
        int pivot = array[right];
        int partition = partition(left, right, pivot);
        quickSort(left,partition-1);
        quickSort(partition+1,right);
    }

    public static void printArray(){
        for(int i:array){
            System.out.print(i + " ");
        }
    }
    public static void swap(int left, int right){
        System.out.println(" Swapping " + array[left] + " with " + array[right] +"\n");
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static int partition(int left, int right, int pivot){
        /**
         * Set left cursor to just outside of the array and right cursor
         */
        int leftPosit = left-1;
        int rightPosit = right;
        while(leftPosit < rightPosit) {
            while (array[++leftPosit] < pivot) ;
            while (rightPosit > 0 && array[--rightPosit] > pivot) ;
            if(leftPosit>=rightPosit){
                break;
            }
            else{
                swap(leftPosit,rightPosit);
            }
        }
        swap(leftPosit, right);
        return leftPosit;
    }

    public static void startPartitioning(){
        for(int i = 0;i<1;i++) {
            int pivot = array[array.length - 1];
            partition(0, array.length - 1, pivot);
        }
    }

    public static void main(String[] args) {
        int[] a = {22,15,12,13,11,20,18,22,14,30,12,3,2,634,565,23,45,6,2,4,77,90};

        quickSortSelf.sortArray(a);
        for (int i:a){
            System.out.print(i + " ");
        }
    }
}
