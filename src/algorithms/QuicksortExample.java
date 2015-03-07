package algorithms;
import java.util.Arrays;

public class QuicksortExample {

    private static int[] a = {22,15,12,13,11,20,18,};
    public static void main(String[] args) {
        // Get a random generated array
        sort();
        System.out.println("\n\n");
        for(int i:a){
            System.out.print(i + " ");
        }
    }

    // This method sorts an array and internally calls quickSort
    public static void sort(){
        int left = 0;
        int right = a.length-1;

        quickSort(left, right);
    }

    // This method is used to sort the array using quicksort algorithm.
    // It takes the left and the right end of the array as the two cursors.
    private static void quickSort(int left,int right){
        if(!(left>=right)) {
            System.out.println("\nPartitioning using pivot "

                    + a[right]);
            System.out.println();
        }
        for(int i = left;i<right+1;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        // If both cursor scanned the complete array quicksort exits.
        if(left >= right) {
            System.out.println("DONE! RETURNING NOW");
            return;
        }

        // For the simplicity, we took the right most item of the array as a pivot
        int pivot = a[right];
        int partition = partition(left, right, pivot);

        // Recursively, calls the quicksort with the different left and right parameters of the sub-array
        System.out.println("Partitioning from " + left + " to " + (partition-1));
        /**
         * Left node left, partition -1 should trigger the equals to case for (left >= right): Return case
         */
        quickSort(left, partition - 1);
        System.out.println("--Partitioning from " + (partition+1) + " to " + right);
        quickSort(partition + 1, right);
    }

    // This method is used to partition the given array and returns the integer which points to the sorted pivot index
    private static int partition(int left,int right,int pivot){
        int leftCursor = left-1;
        int rightCursor = right;
        while(leftCursor < rightCursor){
            while(a[++leftCursor] < pivot);
            while(rightCursor > 0 && a[--rightCursor] > pivot);
            if(leftCursor >= rightCursor){
                break;
            }else{
                swap(leftCursor, rightCursor);
                System.out.println("Swapping " + a[leftCursor] + " with " + a[rightCursor]);
            }
        }
        swap(leftCursor, right);
        System.out.println("Swapping " + a[leftCursor] + " with " + a[right]);
        return leftCursor;
    }

    // This method is used to swap the values between the two given index
    public static void swap(int left,int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

}
