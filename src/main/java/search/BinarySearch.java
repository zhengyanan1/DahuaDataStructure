package search;

/**
 * Created by Administrator on 2017/4/1.
 */
public class BinarySearch {
    static int binarySearch(int[] a,int key){
        int left = 0,right = a.length - 1;
        while (left <= right){
            int middle = (left + right) / 2;
            if (a[middle] == key)       return middle;
            else if (a[middle] > key)   right = middle - 1;
            else                        left = middle + 1;
        }
        return -1;
    }
    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4,5,6,7,8,9,12,15,20,25};
        for (int i = 0; i < 20; i++) {
            System.out.println(binarySearch(a,i));
        }
    }
}
