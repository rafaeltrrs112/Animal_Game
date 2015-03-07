package algorithms;

/**
 * Created by Rtorres on 3/7/2015.
 *
 * Binary search algorithm uses divide and conquer to continuously cut the
 * array in half on the condition whether the value is less or greater than
 * value being searched for. Array must already be sorted for binarySearch to work.
 * TODO make algorithms sort Creature array.
 */
public class binarySearch {
    final int KEY_NOT_FOUND =100;
    public int startSearch(int[] arr, int keyFind){
        return search(arr, keyFind, 0, arr.length-1);
    }

    //Initially invoked with parameters imin = 0 and imax = n-1;
    public int search(int[] A, int key, int imin, int imax){

        //test if array is empty
        if(imax<imin)
            //set is empty, so return value showing not found.
            return KEY_NOT_FOUND;
        else{
            //calculate midpoint to cut set in half
            int imid = midpoint(imin, imax);

            //three way comparison
            if(A[imid] > key)
                //key is in lower subset
                return search(A,key,imin,imid-1);
            //determine which sub array to search.
            else if(A[imid]<key)
                //key is in upper subset
                return search(A,key,imid+1,imax);
            else
                //key has been found.
                return imid;
        }
    }
    public int midpoint(int imin, int imax){
        //java keeps only the whole number when integers are divided.
        return( imin+ (imax - imin)/2 );
    }

    public static void main(String[] args) {
        int[] arrayTest = {1,3,5,4,10,7,4,8,5};
        quickSortSelf.sortArray(arrayTest);
        for(int x:arrayTest){
            System.out.print(x + " ");
        }
        System.out.println();
        binarySearch test = new binarySearch();
        int min = 5;
        int max = 9;
        int findIndex = test.startSearch(arrayTest, 5);
        System.out.println(findIndex);
        String splitter = "name:dog:animal";
        String noSplitter = "holar";
        String []testIt = splitter.split(":");
        String []testNoSplit = noSplitter.split(":");
        System.out.println(java.util.Arrays.toString(testIt));
        System.out.println(java.util.Arrays.toString(testNoSplit));
    }
}
